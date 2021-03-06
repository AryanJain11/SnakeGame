import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    // defining some constants
    // max height, max width set the size of game window
    // tile size defines the side of a square tile
    // delay (ms) in which the frame updates

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    //    public static int max_height, max_width, tile_size;
    public static final int max_height = 600, max_width = 600, tile_size = 25;
    public static int delay = 100;
    int fruitsEaten;
    // currently, unused
    // private static int tileNumber = MAX_HEIGHT * MAX_WIDTH / TILE_SIZE;

    // boolean variable to show game is running or not
    private boolean running = false;

    // Timer object to for the usage of time
    private Timer timer;

    // random object
    Random random;

    // preset direction
    protected char direction = 'R';

    // array of snakes, during the start
    Snake[] snakes = new Snake[2];

    // positions for fruits
    private int fruitPosX, fruitPosY;

    // integer to store player number (as two players can currently play the game)
    public int playerNumber;

    GamePanel(int playerNumberStart) {
//        max_height = (int)(size.getHeight()/2);
//        max_width = max_height;
//        tile_size = (int)(max_height * 0.03125);

        // set the size of JComponent to max_width and max height
        this.setPreferredSize(new Dimension(max_width, max_height));

        // set bg color to black
        this.setBackground(Color.BLACK);

        // set focusable property and add a keyListener to the component
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        // instantiate random
        random = new Random();

        // start a game
        startGame(playerNumberStart);

    }

    // function to start a game, parameter i -> number of players
    public void startGame(int i) {
        // set boolean game running to true
        this.running = true;
        // spawn the first fruit
        spawnFruit();

        // if i == 1, i.e single player game
        if (i == 1) {
            // assign player number
            playerNumber = 1;
            // initialse and start timer
            timer = new Timer(delay, this);
            timer.start();

            // generate new snake
            snakes[0] = new Snake(0, 0);
        }

        // for more than 1 players
        else if (i > 1) {
            // set player number
            playerNumber = i;
            // loop through all snakes
            for (int j = 0; j < i; j++) {
                // generate snake head on random square inside our game surface
                snakes[j] = new Snake(random.nextInt((int) (max_height / tile_size)) * tile_size,
                        random.nextInt((int) (max_width / tile_size)) * tile_size);
            }


            // initialise and start timer
            timer = new Timer(delay, this);
            timer.start();
        }

    }

    // moves all snakes
    public void move() {
        // loop through all the snakes
        for (int j = 0; j < playerNumber; j++) {
            // move each snake
            snakes[j].move();
        }
    }

    // function to randomly generate fruits in the game
    public void spawnFruit() {
        // set a random (x,y) within game boundaries
        fruitPosY = random.nextInt((int) (max_height / tile_size)) * tile_size;
        fruitPosX = random.nextInt((int) (max_width / tile_size)) * tile_size;
    }

    // Swing part below
    // function to paint graphic component on screen
    public void paintComponent(Graphics g) {
        // paint graphic component from the super class (JPanel)
        super.paintComponent(g);

        // call the draw function
        draw(g);
    }

    // the actual function which puts things on the screen
    public void draw(Graphics g) {


//        for(int i=0;i<(max_height/tile_size);i++)
//        {
//            g.drawLine(i*tile_size,0,i*tile_size,max_height);
//            g.drawLine(0,i*tile_size,max_width,i*tile_size);
//        }
        if (running) {
            // the below two for loops make the grid lines
            for (int i = 0; i < (max_width / tile_size); i++) {
                g.drawLine(i * tile_size, 0, i * tile_size, max_height);
            }
            for (int i = 0; i < (max_height / tile_size); i++) {
                g.drawLine(0, i * tile_size, max_height, i * tile_size);
            }

            // this for loop is for drawing all the snakes on the screen

            // for loop for iterating through all the snakes (players)
            for (int j = 0; j < playerNumber; j++) {
                // for loop to iterate through every point of a snake
                for (int i = 0; i < snakes[j].getLength(); i++) {

                    // i = 0 --> this is the snake's head (red color)
                    if (i == 0) {
                        g.setColor(Color.red);
                        g.fillRect(snakes[j].xPos[i], snakes[j].yPos[i], tile_size, tile_size);
                    }
                    // i != 0 --> this is the snake's body (green color)
                    else {
                        g.setColor(Color.green);
                        g.fillRect(snakes[j].xPos[i], snakes[j].yPos[i], tile_size, tile_size);
                    }
                }

//              To display score, currently only for one snake
//                g.setColor(Color.cyan);
//                g.setFont(new Font("Ink Free",Font.BOLD,40));
//                FontMetrics metrics = getFontMetrics(g.getFont());
//
//                for(int r=0;r<playerNumber;r++){
//                    fruitsEaten = snakes[r].getFruitsEaten();
////                    (max_width-metrics.stringWidth("Score: "+ fruitsEaten))/2
//                    g.drawString("Score " + playerNumber + ": "+ fruitsEaten,(max_width-metrics.stringWidth("Score " + playerNumber + ": "+ fruitsEaten))/2 + 10*playerNumber,g.getFont().getSize());
//                }
            }
            if(snakes[1]!=null)
            {
                g.setColor(Color.cyan);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics1 = getFontMetrics(g.getFont());

                fruitsEaten = snakes[0].getFruitsEaten();
                g.drawString("Score 1" + ": "+ fruitsEaten,0,g.getFont().getSize());

                g.setColor(Color.cyan);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics2 = getFontMetrics(g.getFont());

                fruitsEaten = snakes[1].getFruitsEaten();
                g.drawString("Score 2" + ": "+ fruitsEaten,(max_width-metrics2.stringWidth("Score 2" + ": "+ fruitsEaten)),g.getFont().getSize());
            }
            else
            {
                g.setColor(Color.cyan);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics1 = getFontMetrics(g.getFont());

                fruitsEaten = snakes[0].getFruitsEaten();
                g.drawString("Score: "+ fruitsEaten,(max_width-metrics1.stringWidth("Score: "+ fruitsEaten))/2,g.getFont().getSize());
            }

            // food is blue colored
            g.setColor(Color.blue);
            g.fillOval(fruitPosX, fruitPosY, tile_size, tile_size);
        }
        else
        {
            gameOver(g);
        }
    }

    //game over
    JButton button1;
    public void gameOver(Graphics g){
//        To display score, currently only for one snake
        if(snakes[1]!=null)
        {
            g.setColor(Color.cyan);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics1 = getFontMetrics(g.getFont());

            fruitsEaten = snakes[0].getFruitsEaten();
            g.drawString("Score 1" + ": "+ fruitsEaten,0,g.getFont().getSize());

            g.setColor(Color.cyan);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics2 = getFontMetrics(g.getFont());

            fruitsEaten = snakes[1].getFruitsEaten();
            g.drawString("Score 2" + ": "+ fruitsEaten,(max_width-metrics2.stringWidth("Score 2" + ": "+ fruitsEaten)),g.getFont().getSize());
        }
        else {
            g.setColor(Color.cyan);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            fruitsEaten = snakes[0].getFruitsEaten();
            g.drawString("Score: " + fruitsEaten, (max_width - metrics1.stringWidth("Score: " + fruitsEaten)) / 2, g.getFont().getSize());
        }

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(max_width-metrics2.stringWidth("Game Over"))/2,max_height/2);
    }
    // implements actionPerformed method from ActionListener class
    public void actionPerformed(ActionEvent e) {
        // for every action event, all snakes have to be moved
        if(running)
        {
            this.move();

            //terminates the game if number of players left is 1
            if(playerNumber<1){
                this.running=false;
            }

            // the for loops check if any snakes have self collided if collided then cuts the body of the bit snake to where it was collided (seens as snake eating the other) claso checks if the length of the snake is less than 2 then removes the player from the game.
            for (int i = 0; i < playerNumber; i++) {
                for (int j = 0; j < playerNumber; j++) {
                    snakes[i].selfCollision(snakes[j]);
                    if(snakes[i].checkDeath()){
                        for(int m=i;m<playerNumber-1;m++){
                            snakes[m]=snakes[m+1];
                        }
                        playerNumber--;
                    }
                }
            }

            // checks if any snake has eaten a fruit. If yes, a new fruit is spawned
            for (int i = 0; i < playerNumber; i++) {
                if (snakes[i].checkFood(fruitPosX, fruitPosY)) {
                    this.spawnFruit();
                }

            }
            // repaints the screen, so that the updated frame is displayed
            repaint();
        }
    }

    // inner class to handle all keyboard inputs
    // transfer this class to Snakes class if we make multiplayer over network possible, replace snakes[0] with this. and remove the code for second snake
    public class myKeyAdapter extends KeyAdapter {

        // implements keyPressed method from KeyAdapter class
        @Override
        public void keyPressed(KeyEvent e) {
            // key event e is the event of a keyboard key being pressed
            switch (e.getKeyCode()) {
                // get keycode fetches what key has been pressed from the user keyboard
                // the switch case sets the new direction according to the keypress
                case KeyEvent.VK_LEFT:
                    snakes[0].setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    snakes[0].setDirection('R');
                    break;
                case KeyEvent.VK_UP:
                    snakes[0].setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    snakes[0].setDirection('D');
                    break;

                default:
                    break;
            }

            // same piee of code for the second player
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    snakes[1].setDirection('L');
                    break;
                case KeyEvent.VK_D:
                    snakes[1].setDirection('R');
                    break;
                case KeyEvent.VK_W:
                    snakes[1].setDirection('U');
                    break;
                case KeyEvent.VK_S:
                    snakes[1].setDirection('D');
                    break;

                default:
                    break;
            }

        }
    }

}
