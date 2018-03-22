import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Sprite
{
    private int dx; private int dy; private int x = 436; private int y= 268;
    private int defaultx = 435; private int defaulty = 268;
    //dx and dy will be for the rate the sprite is moving
    //x and y are the position of the thing
    private Image image;
    private String typeChar = "";
    private ImageIcon sprite;
    private int[][] arr;
    private boolean movingLeft = false; //these variables will be used so the program knows what point to test for collisions with a border
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingRight = false;
    private int width; private int height; //width and height of the image, will be used to change what corner to use to test for collisions
    private boolean talkNow = false;
    private Pixel pixels;
    public static int LOVE_SCORE = 0;
    public static int MONEY_SCORE = 0;
    public static int EDUCATION_SCORE = 0;
    public static boolean advance;
    
    public Sprite(String type)
    {
        typeChar = type;
        createSprite("Images//" + typeChar + "//Idle.png", x, y); //Creates a sprite of the given image
        pixels = new Pixel("skeleton.png");
    }

    private void createSprite(String filePath, int x1, int y1) //creates a sprite at a given location, using a certain picture
    {
        sprite = new ImageIcon(filePath); 
        image = sprite.getImage();
        width = sprite.getIconWidth();
        height = sprite.getIconHeight();
        x = x1; y = y1;
    }
    
    static void foo() throws IOException {
        throw new IOException("your message");
    }
    
    /*Controls the motion for the sprite, tests  for collisions between the sprite and walls
     * as well as for collisions between sprite and objects in the background. 
     */
    public void move()
    {
        if(movingRight){
            if(x+width <= 898){
                if(pixels.getPixel(x+width,y)|| pixels.getPixel(x+width, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    x--;
                else    
                    x += dx;
            }
        }
        if(movingDown){
            if(y+height <= 550){
                if(pixels.getPixel(x+width,y+height)|| pixels.getPixel(x, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    y--;
                else
                    y += dy;
            }
        }
        if(movingLeft) {//all the similar stuff as to movingRight and Down
            if(x >= 0){
                if(pixels.getPixel(x,y)|| pixels.getPixel(x, y + height)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    x++;
                else
                    x += dx;
            }
        }
        if(movingUp){
            if(y >= 0){
                if(pixels.getPixel(x+width,y)|| pixels.getPixel(x, y)) //if the next pixel is black (from the skeleton), go back to the previous spot
                    y++;
                else
                    y += dy;
            }    
           

        }
          
    }
    /*General getters
     */
    public int getX(){return x;} 
    public int getY(){return y;}
    public boolean isTalking() {return talkNow;}
    public boolean Advance() {return advance;}
    public Image getImg(){return image;}
    public ImageIcon getImgIcn(){return sprite;}

    //Different arrow keys move things
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();        
        /*This portion deals with the movement of the little sprite.
         * We have different images, and then depending on the (x,y) coordinate, they switch, giving the illusion that it's walking
         * The booleans moving<Direction> are changed in this method to see what point to test for collisions
         */
        if (key == KeyEvent.VK_LEFT) {
            dx=-1;
            movingLeft = true;
            movingRight = false;
            movingUp = false;
            movingDown = false;           
            if(x %2 ==0) 
                createSprite("Images//" + typeChar + "//left walk.png", x, y);
            else if(x % 1 == 0)
                createSprite("Images//" + typeChar + "//left walk 2.png", x, y);           
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx=1;
            movingLeft = false;
            movingRight = true;
            movingUp = false;
            movingDown = false;
            if(x % 2 ==0)
                createSprite("Images//" + typeChar + "//right walk.png", x, y);
            else if(x% 1 ==0)
                createSprite("Images//" + typeChar + "//right walk 2.png", x, y);
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            movingLeft = false;
            movingRight = false;
            movingUp = true;
            movingDown = false;
            if(y % 2 == 0)
                createSprite("Images//" + typeChar + "//back walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//back walk 2.png", x, y);

        }

        if (key == KeyEvent.VK_DOWN) {
            movingLeft = false;
            movingRight = false;
            movingUp = false;
            movingDown = true;
            dy = 1;

            if(y% 2 == 0)
                createSprite("Images//" + typeChar + "//front walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//front walk 2.png", x, y);
        }
        if (key == KeyEvent.VK_Z)
        {
            advance = true;
            if ((Math.abs(getX() - Frame.getnpc1x()) <= 50) && (Math.abs(getY() - Frame.getnpc1y()) <= 50))
            {
                //This is where actual VN conversations take place
                //System.out.println("IT WORKS!!");
                if(!talkNow)
                    advance = false;
                talkNow = true;
            }
        }
        if(key ==KeyEvent.VK_X)
        {
            advance = true;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        //Once the key is released, they're returned to the respective idle states.

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            createSprite("Images//" + typeChar + "//left idle.png", x, y);
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            createSprite("Images//" + typeChar + "//right idle.png", x, y);
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            createSprite("Images//" + typeChar + "//back.png", x, y);
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            createSprite("Images//" + typeChar + "//Idle.png", x, y);
        }
    }
}