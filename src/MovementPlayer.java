import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class MovementPlayer implements KeyListener {
    private MyAlien player;
    private Stack<Integer> pressedKeys;
    private static final int ZERO = (KeyEvent.VK_LEFT + KeyEvent.VK_RIGHT) / 2;


    public MovementPlayer(MyAlien player) {
        this.pressedKeys= new Stack<>();
        this.player = player;
        pressedKeys.push(ZERO);

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            if (!pressedKeys.contains(keyCode))
                pressedKeys.push(keyCode);
        }
    }

    public void keyReleased(KeyEvent e) {
        Integer keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
            pressedKeys.remove(keyCode);
    }

    public int getDirection() {
        return pressedKeys.peek() - ZERO;
    }
}