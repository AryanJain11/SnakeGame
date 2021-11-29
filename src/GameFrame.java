import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//frame
public class GameFrame extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JFrame frame;

    GameFrame() {
        frame = new JFrame();
        frame.setSize(749, 411);
        frame.setSize(750, 412);
        frame.setVisible(true);
        frame.setTitle("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // icon
        ImageIcon icon = new ImageIcon("./lib/snake.png");
        frame.setIconImage(icon.getImage());

        // background
        // frame.setLayout(new BorderLayout());
        // JLabel background=new JLabel(new ImageIcon("bk.png"));
        // frame.add(background);
        // background.setLayout(new FlowLayout());

        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(new ImageIcon("bk.png")));
        frame.setLayout(new FlowLayout());
        // button

        button1 = new JButton("Start");
        button1.setBounds(200, 200, 300, 50);
        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setFont(new Font("Comic San", Font.BOLD, 25));
        button1.setBackground(Color.green);

        button2 = new JButton("Single Player");
        button2.setBounds(150, 200, 150, 50);
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setFont(new Font("Comic San", Font.BOLD, 25));
        button2.setBackground(Color.green);

        button3 = new JButton("Multiplayer");
        button3.setBounds(250, 200, 150, 50);
        button3.addActionListener(this);
        button3.setFocusable(false);
        button3.setFont(new Font("Comic San", Font.BOLD, 25));
        button3.setBackground(Color.green);

        frame.add(button1);
        frame.setSize(749, 411);
        frame.setSize(750, 412);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            frame.remove(button1);
            frame.add(button2);
            frame.add(button3);
            SwingUtilities.updateComponentTreeUI(frame);

        } else if (e.getSource() == button2) {
            frame.dispose();
            new GameFrame2(1);

        } else if (e.getSource() == button3) {
            frame.dispose();
            new GameFrame2(2);

        }

    }
}
