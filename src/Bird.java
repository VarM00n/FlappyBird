import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird extends JPanel {
    BufferedImage Bird1;
    BufferedImage Bird2;
    BufferedImage Bird3;
    int changer = 0;
    int falling = 0;
    int gravityForFalling = 2;
    int velocity = 0;
    int position = 0;
    public Bird(){
        try {
            Bird1 = ImageIO.read(new File("res\\Bird1.png"));
            Bird2 = ImageIO.read(new File("res\\Bird2.png"));
            Bird3 = ImageIO.read(new File("res\\Bird3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.changePic(Bird1);
        this.changePic(Bird2);
        this.changePic(Bird3);
        this.setBounds(150,350,72,52);
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

    public void setChanger(int a){
        this.changer = a;
    }

    public void setPosition(int a){
        this.position = a;
    }

    public Rectangle getBounds(){
        return new Rectangle(150, 350 + position, 68, 52);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBounds(150,350 + position, 72,52);
        if(falling == 0){
            if (changer == 0) {
                g.drawImage(Bird1, 0, 0, null);
            }
            if (changer == 1) {
                g.drawImage(Bird2, 0, 0, null);
            }
            if (changer == 2) {
                g.drawImage(Bird3,0, 0, null);
            }
            if (changer == 3) {
                g.drawImage(Bird2,0, 0, null);
            }
        }

    }
}