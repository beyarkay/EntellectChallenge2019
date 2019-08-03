import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 Each task has a cost of 1 shift/unit
Tasks cost double outside the worm speciality
Worms can take a break (free shift)

Worms CANNOT start a new task without completing the first

All worms on break = penalty

[B]iochemist - [D]ome repair
[M]ech Eng - [R]over repair
[S]pace plumber - [P]lumbing
[X]enobiologist - [A]lien classification

<-------------->

3 shifts = day
- Morning (always first)
- Afternoon
- Night

D,0,3,0
Morning 0
Afternoon 3
Night 0

Uncompleted tasks move over to next shift


 */
public class Main {
	
	//Worm types
	static final int WT_B = 0; //Biochemist
	static final int WT_M = 1; //Mechanical engineer
	static final int WT_S = 2; //Space plumber
	static final int WT_X = 3; //Xenobiologist
	
	//Specialities
	static final int SP_D = 0; //Dome repair
	static final int SP_R = 1; //Rover repair
	static final int SP_P = 2; //Plumbing
	static final int SP_A = 3; //Alien classification
	
	
	static int[] workerCounts = new int[4]; //Count for number of each type of worker
	
	static int[][] tasks; //tasks[speciality][shift] = number of tasks for this speciality in this shift
	
	static int N_SHIFTS; //total number of shifts
	
	
	public static void main(String[] args) throws IOException {
		
		readInput(args[0]);
		
		
	
	}
	
	public static void readInput(String fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String[] parts = br.readLine().split(",");
		
		for (int i = 0; i < 4; i++) {
			workerCounts[i] = Integer.parseInt(parts[i]);
		}
		
		parts = br.readLine().split(",");
		
		N_SHIFTS = parts.length - 1;
		
		tasks = new int[4][N_SHIFTS];
		
		for (int i = 0; i < 4; i++) {
			int speciality = charToIndex(parts[0].charAt(0));
			for (int j = 0; j < N_SHIFTS; j++) {
				tasks[speciality][j] = Integer.parseInt(parts[j + 1]);
			}
		}
		
		br.close();
	}
	
	static int charToIndex(char c) {
		switch (c) {
			case 'B':
				return WT_B;
			case 'M':
				return WT_M;
			case 'S':
				return WT_S;
			case 'X':
				return WT_X;
				
			case 'D':
				return SP_D;
			case 'R':
				return SP_R;
			case 'P':
				return SP_P;
			case 'A':
				return SP_A;
		}
		
		System.out.println("failure");
		System.exit(-1);
		return -1;
	}
}
