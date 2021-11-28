import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//frame
public class GameFrame extends JFrame implements ActionListener {
    JButton button;
    JFrame frame;
    GameFrame(){
        frame = new JFrame();
        frame.setSize(749,411);
        frame.setSize(750,412);
        frame.setVisible(true);
        frame.setTitle("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //icon
        ImageIcon icon = new ImageIcon("snake.png");
        frame.setIconImage(icon.getImage());

        //background
//        frame.setLayout(new BorderLayout());
//        JLabel background=new JLabel(new ImageIcon("bk.png"));
//        frame.add(background);
//        background.setLayout(new FlowLayout());

        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\nalwa\\Desktop\\SnakeGame\\bk.png")));
        frame.setLayout(new FlowLayout());
        //button

        button = new JButton("Start");
        button.setBounds(200,200,300,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Comic San",Font.BOLD,25));
        button.setBackground(Color.green);

        frame.add(button);
        frame.setSize(749,411);
        frame.setSize(750,412);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            frame.dispose();
            GameFrame2 frame = new GameFrame2(); //initializes frame (the entire frame of the program)
        }
    }
}
