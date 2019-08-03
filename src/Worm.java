import java.util.Deque;

public class Worm {
	public enum Speciality {
		BIOCHEMIST,
		MECHENGINEER,
		SPACEPLUMBER,
		XENOBIOLOGIST
	}
	
	
	public int motivation;
	public Deque previousShifts;
	
	public Worm() {
	
	}
	
	public Worm(int motivation, Deque previousShifts) {
		this.motivation = motivation;
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
