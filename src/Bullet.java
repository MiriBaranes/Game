import java.awt.*;


public class Bullet {

    public static final int WIDTH = 5;
    public static final int HEIGHT = 15;
    public static final int STRAIGHT_ANGEL = 180;
    public static final int FULL_TURN_ANGLE = 360;

    private MyRectangle body;
    private HalfCircle bulletHead;

    public Bullet(int x, int y) {
        this.body = new MyRectangle(x, y, WIDTH, HEIGHT, Color.blue);
        this.bulletHead = new HalfCircle(x, y, WIDTH, HEIGHT, FULL_TURN_ANGLE, STRAIGHT_ANGEL, Color.CYAN);
    }


    public void paint(Graphics graphics) {
        this.body.paint(graphics);
        this.bulletHead.paint(graphics);

    }

    public void moveUp() {
        this.body.moveUp(Const.BULLET_SPEED);
        this.bulletHead.moveUp(Const.BULLET_SPEED);
    }


    public MyRectangle getBody() {
        return body;
    }

    public boolean collision(Ball ball) {
        Rectangle ballCollision = new Rectangle(ball.getX(), ball.getY(), ball.getW(), ball.getH());
        Rectangle headBullet = new Rectangle(this.bulletHead.getX(), this.bulletHead.getY(), this.bulletHead.getW(), this.bulletHead.getH());

        return ballCollision.intersects(headBullet);
    }


}