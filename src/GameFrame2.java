import javax.swing.*;

/**
 * GamePanel
 */
public class GameFrame2 extends JFrame {
    GameFrame2(){
        this.add(new GamePanel()); //initializes the window panel in the frame this does not include the title bar
        this.setTitle("Snakes"); //sets title of the current frame in the title bar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //by default pressing the close Option will only hide the windoew but process still runs this line actually closes the program
        this.setResizable(false); //set resize to false
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("snake.png");
        this.setIconImage(icon.getImage());
    }

}