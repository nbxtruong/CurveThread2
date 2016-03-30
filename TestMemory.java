import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestMemory extends JFrame {

	private static final String TITLE = "Test Memory";

	public TestMemory() {
		super(TITLE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
	}

	public static void main(String[] args) {
		TestMemory frame = new TestMemory();
		Memory memory = new Memory(100);
		Reading curve = new Reading(memory);
		Acquirement acquirement = new Acquirement(memory, 10);
		frame.getContentPane().add(curve);
		frame.pack();
		frame.setVisible(true);
		acquirement.start();
		new Thread(curve).start();
	}
}
