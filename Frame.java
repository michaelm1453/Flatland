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

public class Frame extends JPanel implements ActionListener
{
    private Sprite sprite;
    private Timer timer;
    private final int DELAY = 10;
    private String type = "1";
    
    public Frame(String typeOfCharacter)
    {
        type = typeOfCharacter;
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
        draw(g);
        Toolkit.getDefaultToolkit().sync(); //syncs the graphics with the Frame
    }
   
    
    
    public void draw(Graphics g)
    {
        ImageIcon background = new ImageIcon("Images//skeleton.png");
        Image background1 = background.getImage();
        Graphics2D graphix = (Graphics2D) g;
        graphix.drawImage(background1, 0,0, this);
        graphix.drawImage(sprite.getImg(), sprite.getX(), sprite.getY(), this); //draws the sprite (this will be really helpful later)
        //ImageIcon npcCircle = new ImageIcon("Images//npcs//circle.png");
        //Image npcCirc = npcCircle.getImage();
        //graphix.drawImage(npcCirc, 230, 230, this);
        //System.out.println(sprite.getX() + "" + sprite.getY());
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //sprite.loadSkeleton("Images//Skeleton.bmp");
        sprite.move(); //if something happens, it moves.
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
        {sprite.keyPressed(e);}
    }

}