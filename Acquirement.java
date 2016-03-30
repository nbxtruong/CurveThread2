public class Acquirement extends Thread {
	private final int size;

	private final Memory memory;

	private final int speed;

	private double phase;

	public Acquirement(Memory memory, int speed) {
		this.memory = memory;
		this.size = memory.size();
		this.speed = speed;
	}

	public void run() {
		int period = 100 / speed;
		while (true) {
			phase = Math.random() * 2 * Math.PI;
			for (int i = 0; i < size; ++i) {
				try {
					sleep(period);
				} catch (InterruptedException e) {
				}
				memory.write(i, function(i));
			}
		}
	}

	public int function(int x) {
		return 60 + (int) (50. * Math.cos(phase + x / 6.));
	}
}
