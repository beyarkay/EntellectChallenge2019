public class Job {
	public Worm worker;
	public int shiftsRemaining;
	public int type;
	
	public void doIt(int shiftNum) {
		if (worker.shouldRest()) {
			worker.restShift();
		} else {
			worker.workShift(shiftNum, type);
			shiftsRemaining--;
		}
	}
	
	public Job(int t) {
		type = t;
	}
	
	public boolean isDone() {
		return shiftsRemaining == 0;
	}
}
