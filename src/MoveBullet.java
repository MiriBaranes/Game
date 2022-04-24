import java.util.ArrayList;

public class MoveBullet extends MyRunnable {
    private Cannon cannon;

    private SpaceListener spaceDetector;
    private ArrayList<Bullet> bullets;


    public MoveBullet(PlayPanel myPlay, Cannon cannon, SpaceListener spaceDetector) {
        super(myPlay);
        this.cannon = cannon;
        this.bullets = myPlay.getBullets();
        this.spaceDetector = spaceDetector;
    }

    @Override
    public void _run() {
        if (spaceDetector.isPressed()) {
            Bullet bullet = new Bullet(cannon.getBodyX() + Cannon.CANNON_W/2, cannon.getBodyY());
            synchronized (bullets) {
                if (this.bullets.size() == 0)
                    this.bullets.add(bullet);
            }
        }
        ArrayList<Ball> balls = myPlay.getComputerBall();
        synchronized (balls) {
            synchronized (bullets) {
                ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
                ArrayList<Ball> ballsToRemove = new ArrayList<>();
                ArrayList<Ball> ballsToAdd = new ArrayList<>();

                for (Bullet bullet : bullets) {
                    bullet.moveUp();
                    if (bullet.getBody().getY() <= 0) {
                        bulletsToRemove.add(bullet);
                    }
                }

                for (Bullet bullet : bullets) {
                    for (Ball ball : balls) {
                        if (bullet.collision(ball)) {
                            ball.hit();
                            myPlay.setPoints();
                            bulletsToRemove.add(bullet);
                            if (ball.isDead()) {
                                ballsToRemove.add(ball);
                            } else {
                                ballsToAdd.add(new Ball(ball));
                            }
                        }
                    }
                }

                bullets.removeAll(bulletsToRemove);
                balls.removeAll(ballsToRemove);
                balls.addAll(ballsToAdd);
            }
        }
        this.myPlay.repaint();

    }

}