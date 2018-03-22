import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frame extends JPanel implements ActionListener
{
    private Sprite sprite;
    private Timer timer;
    private final int DELAY = 10;
    private String type = "1";
    
    //where npcs are located on x y plane
    static int npc1x = 450;
    static int npc1y = 120;
    
    //where talk images are located on x y plane
    private static int npcTalkx = 150;
    private static int npcTalky = 90;
    int charaTalkx = 600;
    int charaTalky = 90;
    private int totalscore = 100;
    File file = new File("Level1Dialogue.txt");
    String dialogue = "TEXT GOES HERE";
    Scanner sc;
    
    public Frame(String typeOfCharacter)
    {
        type = typeOfCharacter;
        try{sc = new Scanner(file);}
        catch(FileNotFoundException e){}
        initFrame();
    }

    private void initFrame()
    {
        addKeyListener(new AAdapter()); //see private class below
        setFocusable(true);
        
        sprite = new Sprite(type);
        timer = new Timer(DELAY, this); //why do you need a timer? for the syncing part. 
        timer.start();//no timer, no movement
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g, sprite.isTalking());
        Toolkit.getDefaultToolkit().sync(); //syncs the graphics with the Frame
    }
    
    public void draw(Graphics g, boolean talk)
    {
        ImageIcon background = new ImageIcon("Images//Background.jpg");
        Image background1 = background.getImage();
        Graphics2D graphix = (Graphics2D) g;
        graphix.drawImage(background1, 0,0, this);
        //graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this); //draws the sprite (this will be really helpful later)
        
        //npcs
        ImageIcon npc1This = new ImageIcon("Images//npcs//" + type + "//idle.png");
        Image npc1 = npc1This.getImage();
        graphix.drawImage(npc1, npc1x, npc1y, this);
        
        //score bars
        graphix.setColor(Color.WHITE);
        graphix.fillRect(0,0, 200, 25);
        graphix.setColor(Color.RED);
        if(education_score(0) < 200)
            graphix.fillRect(0,0, education_score(1), 25);
        else
            graphix.fillRect(0,0, education_score(0), 25);
        graphix.setColor(Color.WHITE);
        graphix.fillRect(0,25, 200, 25);
        graphix.setColor(Color.GREEN);
        if(money_score(0) < 200)
            graphix.fillRect(0,25, money_score(2), 25);
        else
            graphix.fillRect(0,25, money_score(0), 25); 
        graphix.setColor(Color.WHITE);
        graphix.fillRect(0,50, 200, 25);
        graphix.setColor(Color.BLUE);
        if(love_score(0) < 200)
            graphix.fillRect(0,50, love_score(3), 25);
        else
            graphix.fillRect(0,50, love_score(0), 25);
        
        graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this); //I just moved the code so the character is superimposed over the npc
        //System.out.println(sprite.getX() + "" + sprite.getY());
        
        if (talk)
        {
            
            ImageIcon charaTalk = new ImageIcon("Images//" + type + "//idletalk.png");
            Image charaImage = charaTalk.getImage();
            graphix.drawImage(charaImage, charaTalkx, charaTalky, this);
        
            ImageIcon npcTalk = new ImageIcon("Images//npcs//" + type + "//idletalk.png");
            Image npcImage = npcTalk.getImage();
            graphix.drawImage(npcImage, npcTalkx, npcTalky, this);

            ImageIcon textbox = new ImageIcon("Images//textbox.png");
            Image textboxpic = textbox.getImage();
            graphix.drawImage(textboxpic, 63, 290, this);
            graphix.setColor(Color.BLACK);
            graphix.drawString(dialogue, 90, 400);
            if(sprite.advance)
            {
                sc.useDelimiter("\n");
                if(sc.hasNext())
                    dialogue = sc.next();
                else
                {
                    choices(graphix);
                    sprite.advance = false;
                }
                sprite.advance = false;
            }
            
            ImageIcon goon = new ImageIcon("Images//go.png");
            Image go = goon.getImage();
            graphix.drawImage(go, 750, 520, this);
        }
    }
    
    private void choices(Graphics g)
    {
        Graphics2D graphix = (Graphics2D) g;
        graphix.drawString("OK HERE WE GO", 90, 400);
    }
    
    public static int getnpc1x()
    {
        return npc1x;
    }
    
    public static int getnpc1y()
    {
        return npc1y;
    }
    
    public boolean isTalking()
    {
        return sprite.isTalking();
    }
    
    public int love_score(int increase)
    {
        sprite.LOVE_SCORE += increase;
              
        return sprite.LOVE_SCORE;
    }
    
    public int education_score(int increase)
    {
        sprite.EDUCATION_SCORE += increase;
        
        return sprite.EDUCATION_SCORE;
    }
    
    public int money_score(int increase)
    {
        sprite.MONEY_SCORE += increase;
        
        return sprite.MONEY_SCORE;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!sprite.isTalking())
        {
            sprite.move();
        }
        repaint();
    }

    //I might delete this later for conciseness sake and just incorporate it in another part. Until i do that or someone else wants to, this is staying.
    private class AAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {sprite.keyReleased(e);}
        @Override
        public void keyPressed(KeyEvent e)
        {
            sprite.keyPressed(e);
        }
    }

}