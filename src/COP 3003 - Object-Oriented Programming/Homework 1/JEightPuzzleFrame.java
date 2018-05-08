import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JEightPuzzleFrame extends JFrame {
	
    private final String path; // path of the image file
    private int frameWidth; // width of the frame
    private int frameHeight; // height of the frame
    private int buttonWidth; // width of the button
    private int buttonHeight; // height of the button
    private List<JButton> buttons; // list of buttons
    private JPanel panel; // one empty panel
    private final List<JComponent> buttonPositions; // list of button positions

    private Icon extractIcon(int leftTopX, int leftTopY) {
    	// reads the image into a BufferedImage object
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } 
        catch (IOException e) {
            System.err.println("Image not found");
            System.exit(1);
        }

        // allocates another BufferedImage object whose size is
        // the same as the one of the wanted icon
        BufferedImage part = new BufferedImage(buttonWidth, buttonHeight,
                BufferedImage.TYPE_4BYTE_ABGR);

        // copies the data from "image" to "part"
        for (int x = 0; x < buttonWidth; x++) {
            for (int y = 0; y < buttonHeight; y++) {
                part.setRGB(x, y, image.getRGB(x + leftTopX, y + leftTopY));
            }
        }

        // creates an icon whose content is already in "part"
        ImageIcon icon = new ImageIcon();
        icon.setImage(part);

        // returns to the caller
        return icon;
    }

    public JEightPuzzleFrame(String title, String path) { // constructor with two parameters: title of window, path of image
        super(title); // sets the title
        this.path = path; // sets the image path
        
        // calculates the dimensions of the frame and buttons
        calculateButtonDimensions();
       
        setLayout(new GridLayout(3, 3, 0, 0)); // creates a 3x3 grid with no spaces in between
        getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));

        // creates a list of buttonPositions
        buttonPositions = new ArrayList<JComponent>();
        
        // creates an empty button, along with eight buttons that contain images
        emptyPanel();
        
        // sets positions of each button, according to initial figure 
        initialGameSetting();
        pack();
        setLocationRelativeTo(null);
    }   
    
    private void calculateButtonDimensions() {
    	try {
            // reads the image into a BufferedImage object
            BufferedImage image = ImageIO.read(new File(path));

            // gets dimensions of read image
            frameWidth = image.getWidth();
            frameHeight = image.getHeight();

            // calculates dimensions of button from image dimensions
            buttonWidth = frameWidth / 3;
            buttonHeight = frameHeight / 3;
        } 
        catch (IOException f) {
            System.err.println("Image not found");
            System.exit(1);
        }
    }

    private void emptyPanel() {
        panel = new JPanel(); // creates an empty panel for a button
        panel.setSize(buttonWidth, buttonHeight); // sets empty panel to same size as other buttons
        buttons = new ArrayList<JButton>();
        
        // loop creates 8 new buttons 
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                // skips the 9th icon, since that is the empty panel
                if (x == 2 && y == 2) {
                    continue;
                }
                
                // extracts icon with XY coordinates
                Icon icon = extractIcon(x * buttonWidth, y * buttonHeight);

                // creates and sets size for each button
                final JButton button = new JButton();
                button.setSize(buttonWidth, buttonHeight);
                
                // sets icon for each button, with no border
                button.setIcon(icon);
                button.setBorder(null);

                // adds action listener for each button
                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // gets position of empty panel
                        int emptyPanelPosition = Integer.parseInt(panel.getName());
                        
                        // gets position of clicked button
                        int buttonPosition = Integer.parseInt(button.getName());

                        // checks if button can switch with the emptyTile
                        if (Math.abs(emptyPanelPosition - buttonPosition) == 1 || Math.abs(emptyPanelPosition - buttonPosition) == 3) {

                            // switches position
                            panel.setName(String.valueOf(buttonPosition));
                            button.setName(String.valueOf(emptyPanelPosition));

                            // switches on buttonPositions list
                            buttonPositions.remove(emptyPanelPosition);
                            buttonPositions.add(emptyPanelPosition, button);
                            buttonPositions.remove(buttonPosition);
                            buttonPositions.add(buttonPosition, panel);

                            // reloads frame
                            loadBoard();
                            
                            // checks win
                            checkWin();
                        }
                    }
                });
                
                // adds button 
                buttons.add(button);
            }
        }
    }

    private void shuffleButtons() {
        // creates an object for randomization
        Random random = new Random();
        
        // creates a list of positions
        List<Integer> gridPositions = new ArrayList<Integer>();
        
        // adds 8 positions to the list
        for (int i = 0; i < 9; i++) {
            gridPositions.add(i);
        }
        
        // clears the list of buttonPositions
        buttonPositions.clear();
              
        while (!gridPositions.isEmpty()) {
            // randomizes position of button
            int randomPosition = random.nextInt(gridPositions.size());
            int position = gridPositions.get(randomPosition);

            // when the position is equivalent to 8, add the panel
            if (position == 8) {
                panel.setName(String.valueOf(buttonPositions.size()));
                buttonPositions.add(panel);
            } 
            // when it's not equivalent to 8, add a tile
            else {
                JButton gridPlaceholder = buttons.get(position);
                gridPlaceholder.setName(String.valueOf(buttonPositions.size()));
                buttonPositions.add(gridPlaceholder);
            }
            gridPositions.remove(randomPosition);
        }

        // reload the board
        loadBoard();
    }

    private void loadBoard() {
        // remove old component
        for (JComponent gridPlaceholder : buttonPositions) {
            remove(gridPlaceholder);
        }

        // add new component
        for (JComponent gridPlaceholder : buttonPositions) {
            add(gridPlaceholder);
        }
        
        // visualize the change
        getContentPane().validate();
    }

    private void checkWin() {
        boolean won = true;
        
        // Checks each tile for if they are at the right place
        for (int i = 0; i < buttons.size(); i++) {
            
            // if one tile is wrong, sets Won = false then break
            if (!buttons.get(i).getName().equals(String.valueOf(i))) {
                won = false;
                break;
            }
        }

        // if all tiles are orientated correctly
        if (won) {
            
            // congratulates the user for solving the puzzle
            JOptionPane optionPane = new JOptionPane("Congratulations! You have solved the puzzle!", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = optionPane.createDialog("Winner");

            // when user clicks "OK",
            dialog.addComponentListener(new ComponentListener() {

                @Override
                public void componentShown(ComponentEvent arg0) {                 
                }

                @Override
                public void componentResized(ComponentEvent arg0) {                  
                }

                @Override
                public void componentMoved(ComponentEvent arg0) {
                }

                @Override
                public void componentHidden(ComponentEvent arg0) {
                    shuffleButtons();
                }
            });
            dialog.setVisible(true);
        }
    }

    private void initialGameSetting() { // settings for the first time the game runs
        
        // clear the buttons from the list
        buttonPositions.clear();

        // add position for the emptyButton
        panel.setName(String.valueOf(0));
        buttonPositions.add(panel);

        // add position for the rest of the 8 buttons
        buttons.get(0).setName(String.valueOf(1));
        buttonPositions.add(buttons.get(0));

        buttons.get(1).setName(String.valueOf(2));
        buttonPositions.add(buttons.get(1));

        buttons.get(4).setName(String.valueOf(3));
        buttonPositions.add(buttons.get(4));

        buttons.get(5).setName(String.valueOf(4));
        buttonPositions.add(buttons.get(5));

        buttons.get(2).setName(String.valueOf(5));
        buttonPositions.add(buttons.get(2));

        buttons.get(3).setName(String.valueOf(6));
        buttonPositions.add(buttons.get(3));

        buttons.get(6).setName(String.valueOf(7));
        buttonPositions.add(buttons.get(6));

        buttons.get(7).setName(String.valueOf(8));
        buttonPositions.add(buttons.get(7));

        // reload the board
        loadBoard();
    }   
}