import javax.swing.*;
import java.awt.*;


public class PanelMainScreen extends BasicJPanel {
    public final int SIZE = 40;
    private ImageIcon background;
    public PanelMainScreen() {
        super(0, 0, Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H, null);
        this.background=new ImageIcon("b40b8fe8-ced8-4957-866b-c5607896097f.jpg");
        myTitle();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.background.paintIcon(this,g,0,0);
    }
    public void myTitle() {
        JLabel title = new JLabel("Bubble Game", SwingConstants.CENTER);
        title.setFont(new Font("ariel", Font.BOLD, SIZE));
        title.setForeground(Color.cyan);
        title.setBounds(0, Const.TITLE_H, Const.MAIN_WINDOW_W, Const.TITLE_H);
        this.add(title);
    }
}
