import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.Raster;

//More thoughts
//I'm going to need a left border, right border, top border, bottom border
//This way, any direction it's going, it can test along the border to see if anything it touches is black. 
public class Sprite
{
    private int dx; private int dy; private int x = 435; private int y= 268;
    //dx and dy will be for the rate the sprite is moving
    //x and y are the position of the thing
    private Image image;
    private String typeChar = "";
    private ImageIcon sprite;
    private int[][] arr;
    
    public Sprite(String type)
    {
        typeChar = type;
        createSprite("Images//" + typeChar + "//Idle.png", x, y);
    }

    private void createSprite(String filePath, int x1, int y1) //creates a sprite at a given location, using a certain picture
    {
        sprite = new ImageIcon(filePath);
        image = sprite.getImage();
        x = x1; y = y1;
    }
    
    
    public void move()
    {
        if(x >0 && x < 858) //as long as sprite stays within the x-boundaries, he can move
            x += dx;
        else
        {
            if(x == 0) //since the sprite will move 1 more and then be stuck, he gets put back in the field of play
                x++;
            else if(x == 858)
                x --;
        }    
              
        if(y > 0 && y < 515)
            y += dy;
        else
        {
            if(y==1)
                y ++;
            else if(y == 515)
                y--;
        }  
          
    }
    /*General getters
     */
    public int getX(){return x;} 
    public int getY(){return y;}
    public Image getImg(){return image;}
    public ImageIcon getImgIcn(){return sprite;}

    //Different arrow keys move things
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        int centerx = x - (sprite.getIconWidth()/2);
        int centery = y - (sprite.getIconHeight()/2);
        
        /*This portion deals with the movement of the little sprite.
         * We have different images, and then depending on the x/y coordinate, they switch, giving the illusion that it's walking
         */
        if (key == KeyEvent.VK_LEFT) {
            if(Pixel.getPixel(x-1, y))
                dx = -1;
            else
            {
                x++;
                dx = 0;
            }
            
            if(getX() %2 ==0) 
                createSprite("Images//" + typeChar + "//left walk.png", x, y);
            else if(getX() % 2 ==1)
                createSprite("Images//" + typeChar + "//left walk 2.png", x, y);           
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(Pixel.getPixel(x+1, y))
                dx = 1;
            else
            {
                x--;
                dx = 0;
                
            }
            if(x % 2 ==0)
                createSprite("Images//" + typeChar + "//right walk.png", x, y);
            else if(x% 1 ==0)
                createSprite("Images//" + typeChar + "//right walk 2.png", x, y);
        }

        if (key == KeyEvent.VK_UP) {
            if(Pixel.getPixel(x, y-1))
                dy = -1;
            else
            {
                y++;
                dy = 0;
            }
            if(y % 2 == 0)
                createSprite("Images//" + typeChar + "//back walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//back walk 2.png", x, y);

        }

        if (key == KeyEvent.VK_DOWN) {
            if(Pixel.getPixel(x, y+1))
                dy = 1;
            else
            {
                y--;
                dy = 0;
                
            }
            if(y% 2 == 0)
                createSprite("Images//" + typeChar + "//front walk 1.png", x, y);
            else if(y % 1 == 0)
                createSprite("Images//" + typeChar + "//front walk 2.png", x, y);
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