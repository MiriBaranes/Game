import javax.swing.*;
import java.awt.*;

public class BasicJPanel extends JPanel {
    public BasicJPanel(int x, int y, int w, int h, Color color){
        this.setBounds(x,y,w,h);
        this.setBackground(color);
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }

    public void title(String title, int y, int h){
        JLabel jLabel=new JLabel(title,SwingConstants.CENTER);
        jLabel.setFont(Const.FONT);
        jLabel.setBounds(0,y,this.getWidth(),h);
        this.add(jLabel);
    }

}
