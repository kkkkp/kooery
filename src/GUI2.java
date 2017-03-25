import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User interface setup, install windowsbuilder and see design
 * @author Patrick_Pu
 *
 */
public class GUI2 extends JFrame {
	
	/**
	 * Constructor for GUI2
	 */
	public GUI2() {
		/**
		 * setup the JFrame
		 */
		getContentPane().setBackground(SystemColor.inactiveCaption);
		
		getContentPane().setSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(new Dimension(1000, 700));
		setResizable(false);
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
		
		/**
		 * setup buttonlist
		 */
		JPanel buttonList = new JPanel(new GridLayout(10, 1));
		JButton[] buttons = new JButton[10];
		
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("Movie " + (i + 1));
			buttonList.add(buttons[i]);
		}
		JScrollPane scrollPane_1 = new JScrollPane(buttonList);
		scrollPane_1.setBounds(680, 6, 314, 584);
		getContentPane().add(scrollPane_1);
		
		/**
		 * setup displayBox and its scrollpane
		 */
		JTextArea displayBox = new JTextArea();
		displayBox.setWrapStyleWord(true);
		displayBox.setLineWrap(true);
		displayBox.setText("[Kooery] >> Welcome to Kooery! Can't decide on a movie to watch? \n                     Let me identify some movie recommendations for you!\n\n[Kooery] >> How would you like to select your movie recommendation?\n                     1) upcoming releases\n                     2) currently playing\n                     3) genre\n\n");
		displayBox.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setSize(new Dimension(662, 584));

		getContentPane().add(scrollPane);		
		
		scrollPane.setViewportView(displayBox);
		
		/**
		 * setup inputbox and its scrollpane
		 */
		JTextField inputBox = new JTextField();
		inputBox.setToolTipText("");
		inputBox.setBounds(6, 602, 667, 67);
		inputBox.setBackground(Color.WHITE);
		inputBox.addActionListener(new KooeryListener(displayBox, inputBox, buttons));
		getContentPane().add(inputBox);
		inputBox.setColumns(10);
		
		/**
		 * setup labelBox
		 */
		JTextArea labelBox = new JTextArea();
		labelBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		labelBox.setEditable(false);
		labelBox.setText("                                                          Kooery\n                  Provided by Julio, Patrick, Rubab\n                                          @2016 Copyright");
		labelBox.setBackground(SystemColor.inactiveCaption);
		labelBox.setBounds(712, 627, 282, 63);
		getContentPane().add(labelBox);
		setVisible(true);
	}
}
