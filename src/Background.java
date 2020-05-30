import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background extends JPanel {
    private Image backgroundImage;


    public Background(String fileName)throws IOException{
        backgroundImage = ImageIO.read(new File(fileName));

        this.setBounds(0,0,592,1063);
        setBackground(new Color(0,0,0,0));
        this.setVisible(true);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}