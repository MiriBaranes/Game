import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Cannon {
    public static final int CANNON_W = 100;
    public static final int CANNON_H = 80;
    public static final int BALL_W_H = 10;
    public static final int BALL_START_Y = 20;
    public static final int BALL_START_X = 20;
    public static final int DISTANCE_X = 15;
    public static final int GUN_W = 10;
    public static final int GUN_H = 10;
    private ArrayList<ShapeDefined> myPlayer;
    private ArrayList<MyRectangle> life;
    private Circle body;

    public Cannon(int windowW, int windowH) {
        this.body = new Circle((windowW - CANNON_W) / 2, windowH - CANNON_W, CANNON_W, CANNON_H, Color.black.darker());
        this.myPlayer = new ArrayList<>();
        myPlayer.add(body);
        myPlayer.add(new MyRectangle(body.getX() + DISTANCE_X, body.getY() - 10, body.getW() - DISTANCE_X * 2, GUN_H * 2, Color.black.darker()));
        myPlayer.add(new MyRectangle((windowW - GUN_W) / 2, myPlayer.get(1).getY() - GUN_H, GUN_W, GUN_H, Color.black));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.red.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.red.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X * 2, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.red.darker()));
        myPlayer.add(new Circle(this.body.getX() + BALL_START_X + DISTANCE_X * 3, body.getY() + BALL_START_Y, BALL_W_H, BALL_W_H, Color.red.darker()));
        this.life = new ArrayList<>();
        this.life.add(new MyRectangle(body.getX() + DISTANCE_X + 10, body.getY() - 5, BALL_W_H, GUN_H, Color.red));
        this.life.add(new MyRectangle(this.life.get(0).getX() + this.life.get(0).getW() + 10, this.life.get(0).getY(), this.life.get(0).getW(), this.life.get(0).getH(), this.life.get(0).getColor()));
        this.life.add(new MyRectangle(this.life.get(1).getX() + this.life.get(1).getW() + 10, this.life.get(1).getY(), this.life.get(1).getW(), this.life.get(1).getH(),this.life.get(1).getColor()));
        myPlayer.addAll(life);

    }

    public void paint(Graphics graphics) {
        for (ShapeDefined shape : this.myPlayer) {
            shape.paint(graphics);
        }
    }

    public void moveTo(int direction) {
        int movement = direction * Const.SPEED_PLAYER;
        int futurePlace = body.getX() + movement;
        if (futurePlace < 0 || Const.MAIN_WINDOW_W - body.getW() < futurePlace)
            return;
        for (ShapeDefined shape : this.myPlayer) {
            shape.setX(shape.getX() + movement);
        }
    }

    public boolean checkCollision(Ball ball) {
        boolean collision = false;
        Rectangle myCollision = new Rectangle(ball.getX(),
                ball.getY(),
                ball.getW(),
                ball.getH());
        for (ShapeDefined shapeDefined: this.myPlayer){
            if (myCollision.intersects(shapeDefined.getX(),shapeDefined.getY(),shapeDefined.getW(),shapeDefined.getH())){
                collision=true;
                break;
            }
        }
        return collision;
    }

    public void lost(int num) {
        if (num < this.life.size() && num >= 0) {
            this.myPlayer.remove(this.life.get(num));
            this.life.remove(this.life.get(num));
        }
    }

    public int getBodyX() {
        return this.body.getX();
    }

    public int getBodyY() {
        return this.body.getY();
    }

}
