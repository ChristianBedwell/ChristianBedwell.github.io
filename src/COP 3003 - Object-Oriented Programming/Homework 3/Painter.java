// Testing class DrawFrame
import javax.swing.JFrame;

public class Painter {
   public static void main(String[] args) {
      DrawFrame paintProgram = new DrawFrame();
      paintProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      paintProgram.setSize(500, 400);
      paintProgram.setVisible(true);
   } 
} // end class Painter