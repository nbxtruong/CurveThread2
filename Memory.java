public class Memory {
	private final int[] measurements;

	private boolean isProtected;

	public Memory(int size) {
		measurements = new int[size];
		isProtected = true;
	}

	public synchronized void write(int index, int value) {
		while (!isProtected) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		measurements[index] = value;
		if (index == measurements.length - 1) {
			isProtected = false;
			notifyAll();
		}
	}

	public synchronized int[] read() {
		while (isProtected) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		int[] result;
		result = new int[measurements.length];
		for (int i = 0; i < measurements.length; i++) {
			result[i] = measurements[i];
		}
		isProtected = true;
		notifyAll();
		return result;
	}

	public int size() {
		return measurements.length;
	}
}
