import javax.swing.*;
import java.awt.*;

public class Reading extends JPanel implements Runnable {

	private final Memory memory;
	private final int size;
	private static final int HEIGHT = 120;
	private final int width;
	private static final int WAIT = 1000; // 1s
	private static final int SCALE_X = 3;
	private static final int SCALE_Y = 1;

	public Reading(Memory memory) {
		this.memory = memory;
		this.size = memory.size();
		this.width = SCALE_X * (size - 1);
		setPreferredSize(new Dimension(width, HEIGHT));
	}

	public void run() {
		Graphics graphics = getGraphics();

		while (true) {
			int[] vector = memory.read();
			graphics.clearRect(0, 0, width, HEIGHT);
			for (int i = 0; i < size - 1; ++i) {
				graphics.drawLine(SCALE_X * i, SCALE_Y * vector[i],
						SCALE_X * (i + 1), SCALE_Y * vector[i + 1]);
			}
			try {
				Thread.sleep(WAIT);
			} catch (InterruptedException e) {
			}

		}
	}
}
