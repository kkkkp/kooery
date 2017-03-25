import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KooeryListener implements ActionListener {
	/**
	 * class fields
	 */
	private JTextArea display;
	private JTextField input;
	private MovieCategory mc;
	private MovieOutput mo;
	private JButton[] buttons;
	private ButtonListener b;
	
	/**
	 * Constructor
	 * @param display
	 * @param input
	 * @param buttons
	 */
	public KooeryListener(JTextArea display, JTextField input, JButton[] buttons) {
		this.display = display;
		this.input = input;
		this.mc = new MovieCategory();
		this.mo = new MovieOutput();
		this.buttons = buttons;
		this.b = new ButtonListener(display, mo);
		enableButtonListener(buttons, b);
	}
	
	/**
	 * override actionPerformed
	 * retrieve user input from inputBox
	 * talks to IBM Watson API and Movie API
	 * display response to displayBox/buttonList
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		mc.setUserInput(input.getText());
		display.append("[User]     >> " + input.getText() + "\n\n");
		input.setText("");
		
		try {
			mo.processWatsonResponse(mc.getSearchCategory());
			String output = mo.getOutput();
			if (output.startsWith("Movies")) {
				changeButtonText(output, buttons);
				output = "Look at the button bar and choose one you want to know more.";
			}
			if (output.startsWith("Thank")) {
				disable(input, buttons);
			}
			display.append("[Kooery] >> " + output + "\n\n");
		}
		catch (Exception exception) {
			display.append("[Kooery] >> Input is not valid, please try again!\n\n");
		}
	}
	
	/**
	 * enable button listener
	 * @param buttons
	 * @param b
	 */
	private void enableButtonListener(JButton[] buttons, ButtonListener b) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(b);
		}
	}
	
	/**
	 * update button name/label
	 * @param output
	 * @param buttons
	 */
	private void changeButtonText(String output, JButton[] buttons) {
		String[] movies = output.split("Movies:|,");
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(movies[i + 1]);
		}
	}
	
	/**
	 * disable inputBox and buttonList, prevent future user input
	 * @param input
	 * @param buttons
	 */
	private void disable(JTextField input, JButton[] buttons) {
		input.setText("Session is over.");
		input.setBackground(Color.LIGHT_GRAY);
		input.setEnabled(false);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
			buttons[i].setBackground(Color.LIGHT_GRAY);
		}
	}
}