import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class MainGame extends BasicJFrame {
    private PanelMainScreen mainScreen;

    public static void main(String[] args) {
        MainGame start = new MainGame();
    }

    public MainGame() {
        super(Const.MAIN_WINDOW_W,Const.MAIN_WINDOW_H);
        init();
    }
    public void init(){
        this.mainScreen=new PanelMainScreen();
        this.add(mainScreen);
        myBottoms();
        this.setVisible(true);

    }


    public void myBottoms() {
        this.mainBottomsOption((this.getWidth() -Const.BUTTON_START_X), Const.BUTTON_Y_START, Const.BUTTON_W, Const.BUTTON_H,
                "Explanation of the game"
                , this::ExplanationGame);
        this.mainBottomsOption((this.getWidth() -Const.BUTTON_START_X+Const.BUTTON_W), Const.BUTTON_Y_START, Const.BUTTON_W, Const.BUTTON_H, "Start",
                this::startGame);
    }

    public JFrame ExplanationGame() {
        return new ExplanationGame();
    }

    public GameWindow startGame() {
        return new GameWindow(Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H);
    }


    public void mainBottomsOption(int x, int y, int w, int h, String titleOn, Supplier<JFrame> supplier) {
        Button button = new Button(titleOn);
        button.setFont(Const.FONT1);
        button.setBounds(x, y, w, h);
        button.setForeground(Color.cyan.darker());
        button.setBackground(Color.cyan);
        button.addActionListener(e -> {
            JFrame frame = supplier.get();
            frame.setVisible(true);
            this.setVisible(false);
        });
        this.mainScreen.add(button);
    }
}

