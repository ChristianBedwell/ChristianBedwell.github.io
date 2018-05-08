import java.util.*;
import java.awt.*;
import javax.swing.*;

// This class is used to draw a chess board with queens.
// Where the queens are placed on the board is controlled by
// the instance variable: boolean board[][]. The size of board is 8x8.
// Each element in board indicates if a queen is there. For example,
// if board[4][5]==true, there is a queen at row 4 and column 5.
// The value of this 2D array is passed in at the constructor. 
// This class does not decide which cells have queens.
class JChessBoardPanel extends JPanel {
	
	// this variable is used to indicate which cell has a queen
	private boolean board[][];
	
	// the user should pass a 2D boolean array to tell
	// which cells have queens
	public JChessBoardPanel(boolean board[][]) {
		super();
		this.board = board;
	}	
	
	public void drawChessBoard(Graphics g) {
		// get the cell width/height
		int height = getHeight(), width = getWidth();
		int cellHeight = height/8;
		int cellWidth = width/8;

		// creates the checkered-board
		for(int i = 0; i <= height ; i++) {
			for(int j = 0; j <= width ; j++) {
				if((j - i) % 2 == 0) { 
					g.setColor(Color.BLACK);
					g.fillRect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
				}
				else {
					g.setColor(Color.WHITE);
					g.fillRect(j*cellWidth, i*cellHeight, cellWidth, cellHeight);
				}
			}
		}		
	}

	// draws all queens, represented by ovals
	public void drawQueens(Graphics g) {
		int height = getHeight(), width = getWidth();
		int ovalHeight = height/8;	
		int ovalWidth = width/8;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int centerX = j*ovalWidth;
				int centerY = i*ovalHeight;
				
				if(board[i][j] == true) {
					g.setColor(Color.RED);
					g.fillOval(centerX + ovalWidth/4, centerY + ovalHeight/4, ovalWidth/2, ovalHeight/2);
				}				
			}
		}		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawChessBoard(g);
		drawQueens(g);
	}
}

// This JFrame-based class is the container of JChessBoardPanel.
// When the user creates a JEightQueenFrame object, he/she is supposed
// to pass a 2D (8x8) boolean array, board, to tell where the queens should be
// placed.  The size of board is 8x8. Each element in board indicates if a 
// queen is there. For example, if board[4][5]==true, there is a queen at 
// row 4 and column 5.
public class JEightQueenFrame extends JFrame {
	private boolean board[][];
	private JChessBoardPanel chessBoard;
		
	public JEightQueenFrame(boolean board[][]) {
		super();
		this.board=board;
		
		// setting the layout
		getContentPane().setLayout(new BorderLayout());
		
		// adding the ChessBoard
		getContentPane().add(new JChessBoardPanel(board),
			BorderLayout.CENTER);
		
		// other settings
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(590,610);
		setResizable(false);
		setVisible(true);
	}
	
	public static boolean trial(boolean board[][], int n) {
		for(int i = 0; i < 8; i++) {
			board[n][i] = true;
			if(n == 7 && good(board, n, i)) {
				return true;
			}
			if(n < 7 && good(board, n, i) && trial(board, n+1)) {
				return true;
			}
			board[n][i] = false;
		}	// end trial		
		return false;
	}
	
	// checks for conflicts imposed by new queen placements
	private static boolean good(boolean board[][], int n, int i) {
		// checks for queens in same row
		for(int row = n - 1; row >= 0; row--) {
			if(board[row][i] == true) {
				return false;
			}
		}
		
		// checks for queens in upper left diagonal
		for(int row = n - 1, column = i - 1; row >= 0 && column >= 0; row--, column--) {
			if(board[row][column] == true) {
				return false;
			}
		}
		
		// checks for queens in lower left diagonal
		for(int row = n - 1, column = i + 1; row >= 0 && column < 8; row--, column++) {
			if(board[row][column] == true) {
				return false;
			}
		}
		
		// if there are no conflicts, then there is a valid location for new queen
		return true;		
	}	
	
	// declares the board and the frame
	public static void main(String args[]) {
		boolean [][] board = new boolean[8][8];
			
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = false;
			}
		}
		trial(board,0);	// see if we can place a queen at row 0
		JEightQueenFrame queenFrame = new JEightQueenFrame(board);			
	}
} // end class EightQueenFrame