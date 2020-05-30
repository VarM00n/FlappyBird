import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BottomPipe extends JPanel {
    BufferedImage SnakeIcon;
    public int y;
    int velocity = 2;
    int position = 0;


    public BottomPipe(int yz){
        try {
            SnakeIcon = ImageIO.read(new File("res\\PipeBot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.y = yz;
        this.changePic(SnakeIcon);
        this.setBounds(400-position,200,108,1500);
        setBackground(new Color(0,0,0,0));
        this.setVisible(true);
    }

    void changePic(BufferedImage bufferedImage){
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[] pixels = new int[ width * height ];
        pixels = bufferedImage.getRGB( 0, 0, width, height, pixels, 0, width );
        for ( int i = 0; i < pixels.length; i++ ) {
            Color c = new Color( pixels[i] );
            int a = c.getAlpha();
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            if(r == 255 && g == 255 && b == 255) {
                c = new Color(r, g, b, 0);
            }
            else{
                c = new Color(r, g, b);
            }
            pixels[i] = c.getRGB();
        }
        bufferedImage.setRGB( 0, 0, width, height, pixels, 0, width);
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setBounds(400-position,670 - y + 100,108,1500);
        super.paintComponent(g);
        g.drawImage(SnakeIcon,0, 900, null);
    }

}