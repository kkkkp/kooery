import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
/**
 * ActionListener for button
 * @author Patrick_Pu
 *
 */
public class ButtonListener implements ActionListener {
	
	/**
	 * class fields
	 */
	private String text;
	private MovieOutput mo;
	private JTextArea display;
	
	/**
	 * constructor
	 * @param display
	 * @param mo
	 */
	public ButtonListener(JTextArea display, MovieOutput mo) {
		this.text = "";
		this.mo = mo;
		this.display = display;
	}
	
	/**
	 * override actionPerformed
	 * talks to movie api and display response to displayBox
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("javax.swing.JButton".equals(e.getSource().getClass().getName())) {
			JButton button = (JButton) e.getSource();
			String movie = button.getText();
			text = movie.trim();
			String output = "";
			try {
				mo.processWatsonResponse(text);
				output = mo.getOutput();
				display.append("[Kooery] >> " + output + "\n\n");
				text = "";
			}
			catch(Exception exception) {
				display.append("[Kooery] >> Input is not valid, please try again!\n\n");
			}
		}
	}
	
	/**
	 * getter for text
	 * @return
	 */
	public String getButtonText() {
		return text;
	}
}
