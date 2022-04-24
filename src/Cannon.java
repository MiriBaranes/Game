
        import java.awt.*;
        import java.util.ArrayList;

public class Cannon {

    private ArrayList<ShapeDefined> myAlien = new ArrayList<>();
    private Circle body;
    private Circle[] life;
    private MyRectangle gun1;
    private MyRectangle gun2;
    private MyRectangle gun3;
    //private MyRectangle [] gun;
    public final static int CRAFT_W = 150;
    public final static int CRAFT_H = 80;
    public final static int CRAFT_X = (Const.MAIN_WINDOW_W - CRAFT_W)/2;
    public final static int CRAFT_Y = Const.MAIN_WINDOW_H - CRAFT_H - 50;
    public final static int SEAT_H = 40;
    public final static int SEAT_W = 70;
    public final static int SEAT_X = CRAFT_X + (CRAFT_W -  SEAT_W)/2;
    public final static int SEAT_Y = CRAFT_Y + 10;
    public final static int ALIEN_BODY_W = 30;
    public final static int ALIEN_BODY_H = 50;
    public final static int ALIEN_BODY_X = CRAFT_X + (CRAFT_W - ALIEN_BODY_W)/2;
    public final static int ALIEN_BODY_Y = CRAFT_Y + 20;
    public final static int ALIEN_NECK_W = 12;
    public final static int ALIEN_NECK_H = 10;
    public final static int ALIEN_NECK_X = ALIEN_BODY_X + (ALIEN_BODY_W-ALIEN_NECK_W)/2;
    public final static int ALIEN_NECK_Y = ALIEN_BODY_Y - ALIEN_NECK_H/2;
    public final static int ALIEN_HEAD_W = 62;
    public final static int ALIEN_HEAD_H = 40;
    public final static int ALIEN_HEAD_X = CRAFT_X + (CRAFT_W-ALIEN_HEAD_W)/2;
    public final static int ALIEN_HEAD_Y = ALIEN_NECK_Y - ALIEN_HEAD_H;
    public final static int EYE_H = 30;
    public final static int EYE_W = 16;
    public final static int LEFT_EYE_X = ALIEN_HEAD_X + ALIEN_HEAD_W/4 - EYE_W/2;
    public final static int EYE_Y = ALIEN_HEAD_Y + ALIEN_HEAD_H/2 - 10;
    public final static int RIGHT_EYE_X = ALIEN_HEAD_X + 3*ALIEN_HEAD_W/4 - EYE_W/2;
    public final static int LIFE_H = 15;
    public final static int LIFE_W = 15;
    public final static int LIFE1_X = CRAFT_X + (CRAFT_W/3 - LIFE_W)/2;
    public final static int LIFE1_Y = SEAT_Y + SEAT_H - 20;
    public final static int LIFE2_X = CRAFT_X + (CRAFT_W -LIFE_W)/2;
    public final static int LIFE2_Y = LIFE1_Y + 28;
    public final static int LIFE3_X = CRAFT_X + (5*CRAFT_W/3 - LIFE_W)/2;
    public final static int LIFE3_Y = LIFE1_Y;
    public final static int GUN_W = 10;
    public final static int GUN_H = 25;
    public final static int GUN2_X =CRAFT_X + CRAFT_W - GUN_W;
    public final static int GUN2_Y = CRAFT_Y + CRAFT_H/2 - GUN_H;
    public final static int GUN1_Y = GUN2_Y;
    public final static int GUN3_X = CRAFT_X + CRAFT_W/2 - GUN_W/2;
    public final static int GUN3_Y = GUN2_Y;






    public Cannon(){
        this.body = new Circle(CRAFT_X, CRAFT_Y, CRAFT_W, CRAFT_H, Color.PINK);
        this.myAlien.add(new Circle(SEAT_X, SEAT_Y, SEAT_W, SEAT_H, Color.BLACK));
        this.myAlien.add(new HalfC(ALIEN_BODY_X, ALIEN_BODY_Y, ALIEN_BODY_W, ALIEN_BODY_H, 360, 180, Color.GREEN));//BODY
        this.myAlien.add(new MyRectangle(ALIEN_NECK_X, ALIEN_NECK_Y, ALIEN_NECK_W, ALIEN_NECK_H, Color.GREEN));//NECK
        this.myAlien.add(new Circle(ALIEN_HEAD_X, ALIEN_HEAD_Y, ALIEN_HEAD_W, ALIEN_HEAD_H, Color.GREEN));//HEAD
        this.myAlien.add(new HalfC(LEFT_EYE_X, EYE_Y, EYE_W, EYE_H, 360, 180, Color.BLACK));//left Eye
        this.myAlien.add(new HalfC(RIGHT_EYE_X, EYE_Y, EYE_W, EYE_H, 360, 180, Color.BLACK));//Right eye;
        Circle life1 = new Circle(LIFE1_X, LIFE1_Y, LIFE_W, LIFE_H, Color.RED);//life1
        Circle life2 = new Circle(LIFE2_X, LIFE2_Y, LIFE_W, LIFE_H, Color.RED);//life1
        Circle life3 = new Circle(LIFE3_X, LIFE3_Y, LIFE_W, LIFE_H, Color.RED);//life1
        this.life = new Circle[]{life1, life2, life3};
        this.gun1 = new MyRectangle(CRAFT_X, GUN1_Y, GUN_W, GUN_H, Color.BLACK);
        this.gun2 = new MyRectangle(GUN2_X, GUN2_Y, GUN_W, GUN_H, Color.BLACK);
        this.gun3 = new MyRectangle(GUN3_X, GUN3_Y, GUN_W, GUN_H, null);
    }

    public MyRectangle getGun1() {
        return gun1;
    }
    public MyRectangle getGun2() {
        return gun2;
    }
    public MyRectangle getGun3(){
        return  gun3;
    }

    public void setBodyX(int x){
        this.body.setX(x);
    }
    public int getBodyX(){
        return this.body.getX();
    }

    public void setBodyY(int y){
        this.body.setY(y);
    }
    public int getBodyY(){
        return this.body.getY();
    }
    public void paint(Graphics graphics) {
        this.body.paint(graphics);
        for (ShapeDefined shape : this.myAlien) {
            shape.paint(graphics);
        }
        for (int i=0; i< life.length; i++){
            life[i].paint(graphics);
        }
        this.gun1.paint(graphics);
        this.gun2.paint(graphics);
    }

    public void moveTo(int direction) {
        int movement = direction * Const.SPEED_PLAYER;
        int futurePlace = this.body.getX() + movement;
        if (futurePlace < 0 || Const.MAIN_WINDOW_W - this.body.getW() < futurePlace)
            return;
        for (ShapeDefined shape : this.myAlien) {
            shape.setX(shape.getX() + movement);
        }
        for (int i = 0; i < life.length; i++) {
            this.life[i].setX(this.life[i].getX() + movement);
        }
        this.gun1.setX(this.gun1.getX() + movement);
        this.gun2.setX(this.gun2.getX() + movement);
        this.gun3.setX(this.gun3.getX() + movement);
        this.body.setX(this.body.getX() + movement);
    }

    public boolean checkCollision(Ball ball) {
        boolean collision = false;
        Rectangle myCollision = new Rectangle(ball.getX(),
                ball.getY(),
                ball.getW(),
                ball.getH());
        for (ShapeDefined shapeDefined: this.myAlien){
            if (myCollision.intersects(shapeDefined.getX(),shapeDefined.getY(),shapeDefined.getW(),shapeDefined.getH())){
                collision=true;
                break;
            }
        }
        return collision;
    }

    public void lost(int num) {
        if (num < this.life.length && num >= 0) {
            this.life[num].setH(0);
            this.life[num].setW(0);
        }
    }





}