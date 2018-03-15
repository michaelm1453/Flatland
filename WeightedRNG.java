import java.util.Random;
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
import javax.swing.JLabel;
import java.lang.NumberFormatException;

public class WeightedRNG extends JFrame
{
    public static void main (String[] args){
        Random rand = new Random();
        int n = rand.nextInt(100)+1;
        int threshold = 84;
        if(n<threshold)
            System.out.println("Theseus wins!");
        else
            System.out.println("Aeneas wins!");
    }
    public void Generator(){
        JPanel cp = (JPanel) getContentPane();
        setSize(900,600);
        setResizable(false); 
        setLocationRelativeTo(null);
        cp.setLayout(new GridLayout());
        
        JLabel label1 = new JLabel("Competitor 1:");
        JTextField name1 = new JTextField(12);
        cp.add(label1);
        cp.add(name1);
        
        JLabel label2 = new JLabel("Percentage for competitor 1?");
        JTextField percentage1 = new JTextField(12);
        cp.add(label2);
        cp.add(percentage1);
        
        JLabel label3 = new JLabel("Competitor 2:");
        JTextField name2 = new JTextField(12);
        cp.add(label3);
        cp.add(name2);
        
        JLabel label4 = new JLabel("Percentage for competitor 2?");
        JTextField percentage2 = new JTextField(12);
        cp.add(label4);
        cp.add(percentage2);
        
        JButton calculate = new JButton();
        calculate.setText("Calculate");
        calculate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Random rand = new Random();
                    int n = rand.nextInt(100) +1;
                    int threshold = 100-Integer.parseInt(percentage2.getText());
                    if(n<threshold)
                        calculate.setText(name1.getText()+ " wins!");
                    else
                        calculate.setText(name2.getText()+ " wins!");
                }
                catch(NumberFormatException a)
                {}
                
            }
        });
        
        JButton reset = new JButton();
        reset.setText("RESET");
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                name1.setText("");
                name2.setText("");
                percentage1.setText("");
                percentage2.setText("");
                calculate.setText("Calculate");
            }
        });
               
    }

}
