
import java.awt.*;
import java.util.Random;

public class Ball extends Circle {
    private int hp;
    private static final int START_SPEED_X = 2;
    private static final int START_SPEED_Y = -4;
    private static final Random RANDOM = new Random();

    private int speedX;
    private int speedY;


    private Ball(int x, int y, int hp, Color color, int directionX, int directionY) {
        super(x, y, hpToSize(hp), hpToSize(hp), color);
        assert Math.abs(directionX * directionY) != 1;
        speedX = START_SPEED_X * directionX;
        speedY = START_SPEED_Y * directionY;
        this.hp = hp;
    }
    public Ball(Ball ball) {
        this(ball.getX(), ball.getY(), ball.hp, ball.getColor());
        speedX = -ball.speedX;
        speedY = ball.speedY;
    }

    public Ball(int x, int y, int hp, Color color) {
        this(x,
                y,
                hp,
                color,
                getRandomDir(),
                getRandomDir());
    }


    private static int hpToSize(int hp) {/// מחשב את הגודל הגובה והרוחב
        return 30 + 8 * hp;///hp 1-38, 2-46,3-54
    }

    private static int getRandomDir() {
        int dir = RANDOM.nextInt(2) * 2 - 1;
        Utils.sleep(10);
        return dir;
    }

    public void hit() {
        this.hp--;
        if (this.hp > 0)
            System.out.println("now ball hp: " + this.hp);
        this.setH(hpToSize(this.hp));
        this.setW(hpToSize(this.hp));
        up();
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void flipX() {
        this.speedX *= -1;
    }

    public void flipY() {
        this.speedY *= -1;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void step() {
        this.setX(this.getX() + this.getSpeedX());
        this.setY(this.getY() + this.getSpeedY());
    }

    public boolean isDown() {
        return speedY > 0;
    }

    public void up() {
        if (isDown())
            flipY();
    }
}
