import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Worm implements Comparable {
	
	@Override
	public int compareTo(Object o) {
		return 0; //TODO COMPARABLE METHOD
	}
//	private int getPriorityScore(){
//		return
//	}
	
	public enum Speciality {
		BIOCHEMIST,
		MECHENGINEER,
		SPACEPLUMBER,
		XENOBIOLOGIST
	}
	
	public Speciality speciality;
	public int motivation = 15;
	public Deque recentShifts = new ArrayDeque(15);
	//	ArrayList<Integer> previousTasks;
	int[] previousTasks;
	public Job currentJob;
	int consecutiveWorkShifts, consecutiveRestShifts, shiftsSinceWeekend;
	
	
	public Worm() {
	
	}
	
	public boolean shouldRest() {
		if (shiftsSinceWeekend > 14) {
			return true;
		}
		return false;
	}
	
	public void workShift(int shiftNum, int type) {
		previousTasks[shiftNum] = type;
		consecutiveRestShifts = 0;
		consecutiveWorkShifts++;
		shiftsSinceWeekend++;
	}
	
	public void restShift() {
		consecutiveWorkShifts = 0;
		consecutiveRestShifts++;
		if (consecutiveRestShifts >= 3) {
			shiftsSinceWeekend = 0;
		}
	}
	
	public Worm(Speciality speciality) {}

	public Worm(Speciality speciality, int numShifts) {
		this.speciality = speciality;
		this.previousTasks = new int[numShifts];
		Arrays.fill(this.previousTasks, -1);
	}

	public Worm(int motivation, Deque recentShifts) {
		this.motivation = motivation;
		this.recentShifts = recentShifts;
	}

	public Worm(Deque recentShifts) {
		this.recentShifts = recentShifts;
	}
	
	@Override
	public String toString() {
		return "Worm{" +
				"motivation=" + motivation +
				", previousShifts=" + recentShifts +
				'}';
	}
	
	
}
