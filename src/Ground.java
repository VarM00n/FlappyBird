import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground extends JPanel {

    BufferedImage SnakeIcon;
    int position = 0;
    int velocity = 2;

    public Ground(){
        try {
            SnakeIcon = ImageIO.read(new File("res\\Ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(new Color(0,0,0,0));
        this.setBounds(0,1063-260,624,260);
        this.setVisible(true);
    }

    public Rectangle getBounds(){
        return new Rectangle(0, 1036-260, 624, 260);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SnakeIcon, position,0, null);
    }
}