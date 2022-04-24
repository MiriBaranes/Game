import java.awt.*;

public class HalfCircle extends ShapeDefiner {
    private int statAngle;
    private int andAngle;

    public HalfCircle(int x, int y, int width, int height, int statAngle, int andAngle, Color color) {
        super(x, y, width, height, color);
        this.statAngle = statAngle;
        this.andAngle = andAngle;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.fillArc(this.getX(), this.getY(), this.getW(), this.getH(), this.statAngle, this.andAngle);
    }

    public void moveUp(int distance) {
        this.setY(this.getY() - distance);
    }

}