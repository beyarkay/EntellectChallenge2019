import java.io.*;

import java.util.ArrayList;
import java.util.List;

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
<------------>

Worms need a weekend (3 consecutive nothing shifts)
Worms MUST take a WEEKEND or they RESIGN after 5 days with any shift worked
Worms MUST work once every 5 days
Worms MUST NOT have more than 42 motivation

<--------->

Points:
Worms remaining motivation
Completing tasks as close to on time as possible

Validity:
Complete all tasks
One worm employed at the end at least
 */
public class Main {
	
	//Worm types
	static final int WT_B = 0; //Biochemist
	static final int WT_M = 1; //Mechanical engineer
	static final int WT_S = 2; //Space plumber
	static final int WT_X = 3; //Xenobiologist
	
	//Specialities
	static final int TT_D = 0; //Dome repair
	static final int TT_R = 1; //Rover repair
	static final int TT_P = 2; //Plumbing
	static final int TT_A = 3; //Alien classification
	
	//Input
	static int[] workerCounts = new int[4]; //Count for number of each type of worker
	static List<Worm> workers = new ArrayList<Worm>();
	
	static int[][] tasks; //tasks[speciality][shift] = number of tasks for this speciality in this shift
	
	static int N_SHIFTS; //total number of shifts
	
	
	//Scheduler vars
	static int[] remainingTasks = new int[4]; // 23, 43, 0, 3
	
	public static void main(String[] args) throws IOException {

//		readInput(args[0]);
		readInput("input/map_5.input");
		
		
		System.out.println("Done");
		
		//Replace null with a char[][] array, formatted exactly like in the spec document
		writeOutput("output/output.txt", null);
	}
	
	public static void readInput(String fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String[] parts = br.readLine().split(",");
		
		for (int i = 0; i < 4; i++) {
			workerCounts[i] = Integer.parseInt(parts[i]);
		}
		
		{
			for (int j = 0; j < workerCounts[0]; j++) {
				workers.add(new Worm(Worm.Speciality.BIOCHEMIST));
			}
			for (int j = 0; j < workerCounts[1]; j++) {
				workers.add(new Worm(Worm.Speciality.MECHENGINEER));
			}
			for (int j = 0; j < workerCounts[2]; j++) {
				workers.add(new Worm(Worm.Speciality.SPACEPLUMBER));
			}
			for (int j = 0; j < workerCounts[3]; j++) {
				workers.add(new Worm(Worm.Speciality.XENOBIOLOGIST));
			}
		}
		
		parts = br.readLine().split(",");
		
		N_SHIFTS = parts.length - 1;
		if (N_SHIFTS % 3 != 0) {
			System.err.print("Incorrect number of shifts");
			System.exit(-1);
		}
		
		tasks = new int[4][N_SHIFTS];
		
		for (int i = 0; i < 4; i++) {
			
			int speciality = charToIndex(parts[0].charAt(0));
			for (int j = 0; j < N_SHIFTS; j++) {
				tasks[speciality][j] = Integer.parseInt(parts[j + 1]);
			}
			if (i != 3) {
				parts = br.readLine().split(",");
				
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
				return TT_D;
			case 'R':
				return TT_R;
			case 'P':
				return TT_P;
			case 'A':
				return TT_A;
		}

		System.out.println("failure");
		System.exit(-1);
		return -1;}

    public static void writeOutput(String fileName, char[][] worms) throws IOException {


		worms = new char[][]{{'B', 'D', 'D', 'F', 'D', 'D', 'F'},
				{'B', 'D', 'F', 'F', 'D', 'F', 'F'},
				{'M', 'R', 'F', 'F', 'R', 'R', 'F'},
				{'M', 'R', 'F', 'F', 'R', 'R', 'F'}};

		StringBuilder outputString = new StringBuilder("");

		PrintWriter pw = new PrintWriter(new FileWriter(fileName));

		for (int wormIndex = 0; wormIndex < worms.length; wormIndex++) {
			outputString = new StringBuilder();
			for (int shiftIndex = 0; shiftIndex < worms[wormIndex].length; shiftIndex++) {
				outputString.append(worms[wormIndex][shiftIndex]);
			}
			pw.println(String.valueOf(outputString));
		}

		pw.close();
	}

	/*
	Boyd Minimum Viable Product
	 */
	public void mvp() {
		int totalWorkers = 0;
		for (int workerCount : workerCounts) {
			totalWorkers = +workerCount;
		}

		int[][] worms = new int[totalWorkers][N_SHIFTS + 1];


//		for (int i = 0; i < workerCounts.length; i++) {
//			for (int j = 1; j < workerCounts[i]; j++) {
//				worms[i][0] = SP
//
//			}
//		}



		for (int i = 0; i < workerCounts.length; i++) {
			for (int specialityIndex = 0; specialityIndex < tasks.length; specialityIndex++) {
				for (int shiftIndex = 0; shiftIndex < tasks[specialityIndex].length; shiftIndex++) {


					break;
				}
			}

		}
	}


}
