import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DrawFrame extends JFrame implements ItemListener, ActionListener {
   // Array of color choices
   private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK};
   
   // Array of color names
   private String[] colorNames = {"Red", "Green", "Blue", "Black"};
   
   // Array of shape names
   private String[] shapes = {"Rectangle", "Oval", "Line"};
   
   private DrawPanel drawPanel; // panel that handles the drawing
   
   private JButton undoButton; // button to undo the last shape drawn
   private JButton clearButton; // button to clear all shapes
   private JComboBox<String> colorChoices; // combo box for selecting the color
   private JComboBox<String> shapeChoices; // combo box for selecting shapes
   private JCheckBox filledCheckBox; // check box to toggle filled shapes
   
   // DrawFrame constructor
   public DrawFrame() {
      super("Painter"); // name of paint window
      
      // create a panel to store the components at the top of the frame
      JPanel topPanel = new JPanel();

      // create a button for clearing the last shape
      undoButton = new JButton("Undo");
      undoButton.setToolTipText("Undo the previous shape drawn.");
      undoButton.addActionListener(this);
      topPanel.add(undoButton);
      
      // create a button for clearing all shapes
      clearButton = new JButton("Clear");
      clearButton.setToolTipText("Clear all shapes drawn.");
      clearButton.addActionListener(this);
      topPanel.add(clearButton);
      
      // create a combobox for choosing colors
      colorChoices = new JComboBox<String>(colorNames);
      colorChoices.setToolTipText("Select a color.");
      colorChoices.setSelectedItem(null);
      colorChoices.addItemListener(this);
      topPanel.add(colorChoices);

      // create a combobox for choosing shapes
      shapeChoices = new JComboBox<String>(shapes);
      shapeChoices.setToolTipText("Select a shape.");
      shapeChoices.setSelectedItem(null);
      shapeChoices.addItemListener(this);
      topPanel.add(shapeChoices);     
      
      // create a checkbox to determine whether the shape is filled
      filledCheckBox = new JCheckBox("Filled");
      filledCheckBox.setToolTipText("Ensble/disable shape fill.");
      filledCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
      filledCheckBox.addItemListener(this);
      topPanel.add(filledCheckBox);

      // add the top panel to the frame
      add(topPanel, BorderLayout.NORTH);
      
      // create a label for the status bar
      JLabel statusLabel = new JLabel("(0,0)");

      // add the status bar at the bottom
      add(statusLabel, BorderLayout.SOUTH);
            
      // create the DrawPanel with its status bar label
      drawPanel = new DrawPanel(statusLabel);
      
      add(drawPanel); // add the drawing area to the center      
   } 

   // handle selections made to a combobox or checkbox
   public void itemStateChanged(ItemEvent e) {
      if (e.getSource() == shapeChoices) {
    	  drawPanel.setShapeType(shapeChoices.getSelectedIndex());
      }
         
      else if (e.getSource() == colorChoices) {
    	  drawPanel.setDrawingColor(colors[colorChoices.getSelectedIndex()]);
      }
         
      else if (e.getSource() == filledCheckBox) {
    	  drawPanel.setFilledShape(filledCheckBox.isSelected());
      }         
   } 

   // handle undoButton and redoButton clicks
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == undoButton) {
    	  drawPanel.clearLastShape();
      }         
      else if (e.getSource() == clearButton) {
    	  drawPanel.clearDrawing();
      }         
   } 
} 
