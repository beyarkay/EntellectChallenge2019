import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Worm implements Comparable {
	
	@Override
	public int compareTo(Object o) {
		return 0; //TODO COMPARABLE METHOD
	}
	
	public enum Speciality {
		BIOCHEMIST,
		MECHENGINEER,
		SPACEPLUMBER,
		XENOBIOLOGIST
	}
	
	public Speciality speciality;
	public int motivation = 15;
	public Deque previousShifts = new ArrayDeque(15);
	ArrayList<Integer> previousTasks;
	public Job currentJob;
	
	public Worm() {
	
	}
	
	public Worm(Speciality speciality) {
		this.speciality = speciality;
	}
	
	public Worm(int motivation, Deque previousShifts) {
		this.motivation = motivation;
		this.previousShifts = previousShifts;
	}
	
	public Worm(Deque previousShifts) {
		this.previousShifts = previousShifts;
	}
	
	@Override
	public String toString() {
		return "Worm{" +
				"motivation=" + motivation +
				", previousShifts=" + previousShifts +
				'}';
	}
	
	
}
