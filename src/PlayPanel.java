import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class PlayPanel extends BasicJPanel {
    public static final int START_LIVES = 3;
    private static final int MAX_LEVEL = 7;
    public final int MESSAGE_HEIGHT = 100;
    public final String GAME_OVER_MESSAGE = "Game Over!";
    public final String WON_MESSAGE = "You won!";
    public final String COME_BACK_MESSAGE = "Click to go back!";
    public final String PLAY_AGAIN_MESSAGE = "Click to play again!";
    public final int DISTANCE = 20;


    private SpaceListener spaceDetector;
    private ArrayList<Bullet> bullets;
    private ArrayList<Ball> computerBall;
    private ArrayList<MyRunnable> allRunnableMethods;
    private int spentLives;
    private MyAlien cannon;
    private int points;
    private int level;
    private JLabel levelText;
    private ImageIcon backGround;
    private int hpBall;


    public PlayPanel(int x, int y, int width, int height) {
        super(x, y, width, height, Color.WHITE);
        this.setBounds(x, y, width, height);
        this.computerBall = new ArrayList<>();
        this.hpBall=0;
        this.bullets = new ArrayList<>();
        this.allRunnableMethods = new ArrayList<>();
        this.cannon = new MyAlien();
        this.spaceDetector = new SpaceListener();
        this.spentLives = 0;
        this.points = 0;
        this.level = 1;
        this.backGround = new ImageIcon("img.png");
        this.addKeyListener(spaceDetector);
        initPlay();
    }

    public void initPlay() {
        movePlayer();
        moveBullet();
        this.addLastBall();
        moveComputerBallLoop();
        initMessage();
    }
    public void changeHp(){
        this.hpBall++;
    }

    public void setLevelText() {
        this.levelText.setText("Level  :" + this.level + "\n" + "   Lives left: " + (START_LIVES - this.spentLives)
                + "   Score: " + this.points);
    }

    public void initMessage() {
        this.levelText = new JLabel("", SwingConstants.CENTER);
        levelText.setBounds(0, 0, this.getWidth(), Const.FONT.getSize());
        levelText.setFont(Const.FONT);
        levelText.setForeground(Color.red.darker());
        setLevelText();
        this.add(levelText);
    }

    public void setPoints() {
        this.points++;
        setLevelText();
    }

    public void setLevel() {
        this.level++;
        setLevelText();
    }

    public MyAlien getCannon() {
        return this.cannon;
    }

    public int getLife() {
        return this.spentLives;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    public void moveBullet() {
        MoveBullet moveBullet = new MoveBullet(this, this.cannon, this.spaceDetector);
        this.allRunnableMethods.add(moveBullet);
        new Thread(moveBullet).start();
    }

    public void movePlayer() {
        MovePlayer movementPlayer = new MovePlayer(this, this.cannon);
        this.allRunnableMethods.add(movementPlayer);
        new Thread(movementPlayer).start();
    }

    public void moveComputerBallLoop() {
        MoveBall moveBall = new MoveBall(this);
        this.allRunnableMethods.add(moveBall);
        new Thread(moveBall).start();
    }

    public boolean isGameOver() {
        return this.getLife() == START_LIVES || this.level > MAX_LEVEL;
    }

    public void stop() {
        this.levelText.setVisible(false);
        for (MyRunnable myRunnable : this.allRunnableMethods) {
            myRunnable.stop();
        }
        BasicJPanel message = new BasicJPanel(0, this.getHeight() / 2 - MESSAGE_HEIGHT, this.getWidth(), MESSAGE_HEIGHT, Color.red);
        String myMessage = "";
        if (this.level <= MAX_LEVEL) {
            myMessage = GAME_OVER_MESSAGE;
        } else myMessage = WON_MESSAGE;
        message.title(myMessage, 0, message.getHeight());
        this.add(message);
        backButtonGame();
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        synchronized (computerBall) {
            super.paintComponent(g);
            this.backGround.paintIcon(this, g, -25, 0);
            this.cannon.paint(g);
            synchronized (computerBall) {
                for (Ball ball : this.computerBall) {
                    ball.paint(g);
                }
            }
            synchronized (bullets) {
                for (Bullet bullet : this.bullets) {
                    bullet.paint(g);
                }
            }
        }
        this.cannon.paint(g);
    }

    public ArrayList<Ball> getComputerBall() {
        return this.computerBall;
    }

    public void addLastBall() {
        this.computerBall.add(randomBall());
    }

    public Ball randomBall() {
        Random random = new Random();
        Color color = Color.getHSBColor((float) Math.random(), 1, (float) Math.random());
        changeHp();
        return new Ball(random.nextInt(getWidth() - Const.MAIN_WINDOW_W/5) + DISTANCE, DISTANCE,hpBall, color);
    }

    public void moveBall(Ball ball) {
        int hw = ball.getW();
        if (ball.getY() <= 0 || this.getHeight() - hw <= ball.getY())
            ball.flipY();
        if (ball.getX() <= 0 || this.getWidth() <= ball.getX() + hw - DISTANCE/2)
            ball.flipX();

        ball.step();
    }

    public void hit() {
        spentLives++;
        this.setLevelText();
    }

    public void backButtonGame() {
        addButton(() -> new GameWindow(Const.MAIN_WINDOW_W, Const.MAIN_WINDOW_H), this.getHeight() - MESSAGE_HEIGHT*3, PLAY_AGAIN_MESSAGE);
        addButton((MainGame::new), this.getHeight() - MESSAGE_HEIGHT*2, COME_BACK_MESSAGE);

    }

    public void addButton(Supplier<JFrame> jFrameSupplier, int y, String title) {
        Button buttonBack = new Button(title);
        buttonBack.setBounds(0, y, Const.MAIN_WINDOW_W, MESSAGE_HEIGHT);
        buttonBack.setBackground(Color.green.darker());
        buttonBack.addActionListener((e -> {
            JFrame jFrame = jFrameSupplier.get();
            jFrame.setVisible(true);
            (SwingUtilities.getAncestorOfClass(JFrame.class, this)).setVisible(false);
        }));
        this.add(buttonBack);
    }
}