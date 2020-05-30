import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TopPipe extends JPanel {
    BufferedImage SnakeIcon;
    int x = 0;
    public int y;
    int velocity = 2;
    int position = 0;

    public TopPipe(){
        try {
            SnakeIcon = ImageIO.read(new File("res\\PipeTop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.changePic(SnakeIcon);
        y = -getRandomNumberInRange(250) - 250; //do 500 od 250
        this.setBounds(400-position,y,108,670);
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

    private static int getRandomNumberInRange(int max) {
        int random = (int)(max * Math.random() + 1);
        return random;
    }


    public void changeY(){
        y = -getRandomNumberInRange(250) - 250;
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setBounds(400-position,y,108,670);
        super.paintComponent(g);
        g.drawImage(SnakeIcon,0, 0, null);
    }

}