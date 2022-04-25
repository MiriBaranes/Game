

import javax.swing.*;
import java.awt.*;

public class ExplanationGame extends BasicJFrame{
    public final int X = 0;
    public final int Y = 200;
    public final int WIDTH = 1000;
    public final int HEIGHT = 50;
    public final int SIZE = 20;
    public ExplanationGame() {
        super(Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H);
        text();
        backButton();
    }
    public void text() {
        Font font= new Font("Ariel",Font.BOLD, SIZE);
        JLabel textMenu1=addJLabel("Left ⬅",X,Y,WIDTH,HEIGHT, font);
        JLabel textMenu2=addJLabel("Right ➡",X,textMenu1.getY()+textMenu1.getHeight(),WIDTH,HEIGHT, font);
        JLabel textMenu3=addJLabel("Space = Shots \uD83D\uDD2B",X,textMenu2.getY()+textMenu2.getHeight(),WIDTH,HEIGHT, font);
        JLabel textMenu4=addJLabel("Life Span = 3 ",X,textMenu3.getY()+textMenu3.getHeight(),WIDTH,HEIGHT, font);
        JLabel textMenu5 = addJLabel(" The goal: you have 7 level! You need to escape the balls"
                , X, textMenu4.getY()+textMenu4.getHeight(), WIDTH,HEIGHT, font);
    }
    public JLabel addJLabel (String s, int x, int y, int w, int h, Font font) {
        JLabel jLabel = new JLabel(s,SwingConstants.CENTER);
        jLabel.setBounds(x, y, w, h);
        jLabel.setFont(font);
        this.add(jLabel);
        return jLabel;
    }
    public void backButton(){
        Button button= new Button("back");
        button.setBounds(0,0,WIDTH,HEIGHT);
        button.setBackground(Color.red);
        button.addActionListener((e -> {
            MainGame mainStartOption= new MainGame();
            this.setVisible(false);
        }));
        this.add(button);
    }
}
