public abstract class MyRunnable implements Runnable {
    private boolean running = true;
    protected final PlayPanel myPlay;
    private static final int SPEED =16;

    public MyRunnable(PlayPanel myPlay) {
        this.myPlay = myPlay;
    }

    public void stop() {
        running = false;
    }

    public abstract void _run();

    @Override
    public void run() {
        while (running) {
            this._run();
            Utils.sleep(SPEED);
        }
    }
}
