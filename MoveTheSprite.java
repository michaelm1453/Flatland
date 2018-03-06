import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JApplet;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.util.Scanner;

public class MoveTheSprite extends JFrame
{
    public static String typeOfChar = "";
    public MoveTheSprite(){ //constructor for the main menu
        MainMenu();
    }
    
    public MoveTheSprite(String charact)//constructor for the level
    {
        initUI(charact);
    }
    private void initUI(String character)
    {
        add(new Frame(character));
        setSize(900,600);
        setResizable(false);
                
        setTitle("Sprite Moving");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //general JFrame things
    }
    public void MainMenu()
    {
        JPanel cp = (JPanel) getContentPane();
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        cp.setLayout(new GridLayout()); //gets it to line up in the center
        
        JButton button = new JButton();
        button.setBounds(100,100,100,100);
        button.setText("circle");
        ImageIcon circle = new ImageIcon("Images//circle//Idle.png");
        button.setIcon(circle);
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button.getText();
                    TestMethod(cp, typeOfChar);
                }
            });
        cp.add(button);
         
        JButton button1 = new JButton();
        button1.setText("square");
        ImageIcon square = new ImageIcon("Images//square//Idle.png");
        button1.setIcon(square);
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button1.getText();
                    TestMethod(cp, typeOfChar);
                }
            });
        cp.add(button1);
        
        JButton button2 = new JButton();
        button2.setText("heart");
        ImageIcon heart = new ImageIcon("Images//heart//Idle.png");
        button2.setIcon(heart);
        button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button2.getText();
                    TestMethod(cp, typeOfChar);
                }
            });        
        cp.add(button2);
    
        JButton button3 = new JButton();
        button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button3.getText();
                    TestMethod(cp, typeOfChar);
                }
            });
        button3.setText("triangle");
        ImageIcon triangle = new ImageIcon("Images//Triangle//Idle.png");
        button3.setIcon(triangle);
        cp.add(button3);
        
        JButton button4 = new JButton();
        button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button4.getText();
                    TestMethod(cp, typeOfChar);
                }
            });
        button4.setText("star");
        ImageIcon star = new ImageIcon("Images//star//Idle.png");
        button4.setIcon(star);
        cp.add(button4);
        
        JButton button5 = new JButton();
        button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    typeOfChar = button5.getText();
                    TestMethod(cp, typeOfChar);
                }
            });
        button5.setText("diamond");
        ImageIcon diamond = new ImageIcon("Images//diamond//Idle.png");
        button5.setIcon(diamond);
        cp.add(button5);
    }
    
    private void TestMethod(JPanel panel, String typeOfChar)
    {
        panel.removeAll();
        panel.updateUI();
        dispose();
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MoveTheSprite example1 = new MoveTheSprite(typeOfChar);
                example1.setVisible(true);
            }
        });
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()//so we need this runnable. need to look into these more.
        {
            @Override
            public void run() 
            {  
                MoveTheSprite example = new MoveTheSprite();
                example.setVisible(true);                               
            }
            });
    }   
}

