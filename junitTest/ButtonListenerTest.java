import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.junit.Test;

public class ButtonListenerTest {
	private JButton b;
	private ButtonListener listnr;
	private MovieOutput mo;
	private JTextArea display;
	
	public ButtonListenerTest() {
		this.b = new JButton();
		this.mo = new MovieOutput();
		this.display = new JTextArea();
		this.listnr = new ButtonListener(display, mo);
	}
	@Test
	public void testGetButtonTextWithDefault() {
		boolean isStartingWith = false;
		b.addActionListener(listnr);
		b.doClick();
		
		isStartingWith = display.getText().contains("not valid");
		assertEquals("Click the button should not work", isStartingWith, true);
	}
	
	@Test
	public void testWithAnotherButton() {
		JButton c = new JButton("Arrival");
		try {
			boolean isStartingWith = false;
			mo.processWatsonResponse("upcoming");
			c.addActionListener(listnr);
			c.doClick();
			
			isStartingWith = display.getText().contains("What detail about this movie");
			assertEquals("Click the button should set text to be null", true, isStartingWith);
		}
		catch(Exception e) {
			assertEquals("This should never be reached", true, false);
		}
		
	}
}
