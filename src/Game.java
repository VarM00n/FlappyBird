import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Game extends JFrame {

    private Background background;
    {
        try {
            background = new Background("res\\BackgroundDay.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_SPACE)
                birdJump = true;
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    };

    /**
     * Variables
     */
    Ground gGround1 = new Ground();
    Ground gGround2 = new Ground();
    Bird bird = new Bird();
    TopPipe topPipe = new TopPipe();
    BottomPipe bottomPipe = new BottomPipe(topPipe.getY());
    TopPipe topPipeTwo = new TopPipe();
    BottomPipe bottomPipeTwo = new BottomPipe(topPipe.getY());
    int ibirdchanger = 0;
    int counterBirdImage = 0;
    int counterBirdPosition = 0;
    int counterPipePosition = 0;
    boolean birdJump = false;

    public Game() throws InterruptedException {
        super("Flappy Bird");
        setSize(592,1063);
        add(gGround2); add(gGround1);
        add(bird);
        add(topPipe);
        add(bottomPipe);
        add(background);
        addKeyListener(keyListener);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        play();
    }

    public void UpdateGround(){
        gGround1.position = gGround1.position - gGround1.velocity;
        gGround2.position = gGround2.position - gGround2.velocity;
        if(gGround2.position == 0){
            gGround1.position = gGround2.position + 624;
        }
        if(gGround1.position == 0){
            gGround2.position = gGround1.position + 624;
        }
    }

    public Rectangle getBoundsForGround(){
        return new Rectangle(0, 1036-240, 624, 260);
    }

    public boolean collisionForGround(){
        return getBoundsForGround().intersects(bird.getBounds());
    }

    public boolean collisionForTopPipeOne(){
        return getBoundsForFirstTopPipe().intersects(bird.getBounds());
    }

    public boolean collisionForbottomPipeOne(){
        return getBoundsForFirstBotPipe().intersects(bird.getBounds());
    }

    public void UpdateFirstPipe(){
        topPipe.position = topPipe.position + topPipe.velocity;
        bottomPipe.position = bottomPipe.position + bottomPipe.velocity;
        if(topPipe.position == 200){
            topPipe.position = 0;
            topPipe.changeY();
        }
    }

    public Rectangle getBoundsForFirstTopPipe(){
        return new Rectangle(topPipe.getX(), topPipe.y, 108,670);
    }

    public Rectangle getBoundsForFirstBotPipe(){
        return new Rectangle(bottomPipe.getX(), topPipe.y + 670 +230, 108,670);
    }

    public void UpdateBird(){
        counterBirdImage++;
        if(counterBirdImage == 5){
            counterBirdImage = 0;
            ibirdchanger++;
        }
        ibirdchanger = ibirdchanger%4;
        bird.setChanger(ibirdchanger);
        counterBirdPosition++;
        if(birdJump){
            birdJump = false;
            bird.velocity = -6;
        }
        bird.position = bird.position + bird.velocity;
        if (counterBirdPosition == 10) {
            counterBirdPosition = 0;
            bird.velocity = bird.velocity + bird.gravityForFalling;
        }
        if(bird.velocity >= 0) {
            bird.position = bird.position + bird.velocity;
        }
    }

    public void play() throws InterruptedException {
        gGround2.position = 624;
        while(true){
            Thread.sleep(15);
            UpdateFirstPipe();
            UpdateGround();
            UpdateBird();
            repaint();
            if(collisionForGround()){
                break;
            }
            if(collisionForTopPipeOne()){
                break;
            }
            if(collisionForbottomPipeOne()){
                break;
            }
        }
    }



}
