import javax.swing.*;

/**
 * Created by 中奇 on 2017/9/15.
 */
public class Main extends JFrame {
    private Reversi drawReversiBoard;

    public Main() {
        drawReversiBoard = new Reversi();

        setTitle("Reversi");
        setContentPane(drawReversiBoard);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        addMouseListener(drawReversiBoard);


    }

    public static void main(String[] args) {

        int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        Main m = new Main();
        m.setSize(800, 800);
        m.setVisible(true);
        m.setLocation((screenWidth - 800) / 2, (screenHeight - 800) / 2);

    }
}