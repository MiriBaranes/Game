import java.util.ArrayList;

public class MovePlayer extends MyRunnable {
    private MovementPlayer movementPlayer;
    private MyAlien cannon;
    private boolean touching = false;

    public MovePlayer(PlayPanel playPanel, MyAlien myCannon) {
        super(playPanel);
        this.cannon = myCannon;


        movementPlayer = new MovementPlayer(this.cannon);
        myPlay.setFocusable(true);
        myPlay.setVisible(true);
        myPlay.requestFocus();
        myPlay.addKeyListener(movementPlayer);
    }

    @Override
    public void _run() {
        cannon.moveTo(movementPlayer.getDirection());
        ArrayList<Ball> balls = this.myPlay.getComputerBall();
        boolean lost = false;
        synchronized (balls) {
            for (Ball ball : balls) {
                if (this.myPlay.getCannon().checkCollision(ball)) {
                    lost = true;
                    ball.up();
                    break;
                }
            }
        }
        if (lost) {
            if (!touching) {
                myPlay.getCannon().lost(this.myPlay.getLife());
                myPlay.hit();
            }
            this.touching = true;
        } else this.touching = false;
        if (myPlay.isGameOver()){
            this.myPlay.stop();
        }
    }
}
