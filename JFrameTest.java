import javax.swing.*;
import java.awt.*;

public class JFrameTest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("JFrame Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setLayout(new FlowLayout());

		JLabel label = new JLabel("This is a test");
		//label.setHorizontalTextPosition(JLabel.BOTTOM);
		//label.setVerticalTextPosition(JLabel.CENTER);
		label.setBounds(100,100,150,30);
		panel.add(label);

		//JButton button = new JButton("Test Button");
		//button.setSize(300, 30);

		//button.setLocation(0,0);
		//panel.add(button);


		frame.setContentPane(panel);
		frame.setSize(800,600); //eventually get this to be the dimensions of the screen, but, you know, effort.
		frame.setLocationRelativeTo(null); //centers it
		frame.setVisible(true);

	}
}