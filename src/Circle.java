import java.awt.*;

public class Circle extends ShapeDefiner {

    public Circle(int x, int y, int width, int height, Color color) {
        super(x,y,width, height, color);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(this.getColor());
        graphics.fillOval(this.getX(), this.getY(), this.getW(), this.getH());

    }
}