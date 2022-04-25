import java.util.ArrayList;

public class MoveBall extends MyRunnable {


    public MoveBall(PlayPanel myPlay) {
        super(myPlay);
    }

    @Override
    public void _run() {
        ArrayList<Ball> balls = myPlay.getComputerBall();
        synchronized (balls) {
            for (Ball ball : myPlay.getComputerBall()) {
                myPlay.moveBall(ball);
            }
            myPlay.getComputerBall().removeIf(ball -> ball.getW() == 0);
            if (myPlay.getComputerBall().size() <= 0) {
                myPlay.setLevel();
                if (this.myPlay.getLevel()<PlayPanel.MAX_LEVEL){
                    myPlay.addLastBall();
                }
            }
            this.myPlay.repaint();
        }
    }

}
