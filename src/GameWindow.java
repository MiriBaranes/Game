import javax.swing.*;

public class GameWindow extends BasicJFrame {

    public GameWindow(int w, int h) {
        super(w, h);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayPanel gamePanel = new PlayPanel(0,0, this.getWidth(), this.getHeight());
        this.add(gamePanel);


    }




}
