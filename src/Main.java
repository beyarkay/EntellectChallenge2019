import java.io.*;
import java.util.*;

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

	//Task Types
	static final int TT_D = 0; //Dome repair
	static final int TT_R = 1; //Rover repair
	static final int TT_P = 2; //Plumbing
	static final int TT_A = 3; //Alien classification
	static final int TT_F = -1; // Free
	private static boolean DEBUG = true;

	//Input
	static int[] workerCounts = new int[4]; //Count for number of each type of worker
	static List<Worm> workers = new ArrayList<Worm>();

	static int[][] tasks; //tasks[speciality][shift] = number of tasks for this speciality in this shift

	static int N_SHIFTS; //total number of shifts


	//Scheduler vars
	static int[] remainingTasks = new int[4]; // 23, 43, 0, 3
	static PriorityQueue<Worm>[] workerQueues = new PriorityQueue[4];
	static ArrayList<Job> jobsList = new ArrayList<>();

	private static void runScheduler() {
		while (doTasksRemain()) {
			//1 find optimal workers
//			while(remainingTasks[0] != 0 && workersB)
//			{
//
//			}
			//2 assign all workers
		}
	}

	private static boolean doTasksRemain() {
		return true; //TODO fixme
	}


	public static void main(String[] args) throws IOException {

		String[] inFiles = new String[]{
				"input/map_1.input"
				,
				"input/map_2.input",
				"input/map_3.input",
				"input/map_4.input",
				"input/map_5.input"
		};
		String[] outFiles = new String[]{
				"output/map_1.txt"
				,
				"output/map_2.txt",
				"output/map_3.txt",
				"output/map_4.txt",
				"output/map_5.txt"
		};
		DEBUG = true;
		for (int i = 0; i < inFiles.length; i++) {
			readInput(inFiles[i]);
			System.out.println("Done Reading file " + inFiles[i]);
			mvp(outFiles[i]);
			System.out.println("Done MVPing file " + inFiles[i] + "\n");
//			if (DEBUG) {
//				break;
//			}

		}
	}

	public static void readInput(String fileName) throws IOException {
		workers = new ArrayList<Worm>();


		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String[] parts = br.readLine().split(",");

		for (int i = 0; i < 4; i++) {
			workerCounts[i] = Integer.parseInt(parts[i]);
		}
		parts = br.readLine().split(",");

		N_SHIFTS = parts.length - 1;

		for (int j = 0; j < workerCounts[0]; j++) {
			workers.add(new Worm(Worm.Speciality.BIOCHEMIST, N_SHIFTS));
		}
		for (int j = 0; j < workerCounts[1]; j++) {
			workers.add(new Worm(Worm.Speciality.MECHENGINEER, N_SHIFTS));
		}
		for (int j = 0; j < workerCounts[2]; j++) {
			workers.add(new Worm(Worm.Speciality.SPACEPLUMBER, N_SHIFTS));
		}
		for (int j = 0; j < workerCounts[3]; j++) {
			workers.add(new Worm(Worm.Speciality.XENOBIOLOGIST, N_SHIFTS));
		}


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
			case 'F':
				return TT_F;
		}

		System.out.println("failure");
		System.exit(-1);
		return -1;
	}

	static char WTToChar(int i) {
		switch (i) {
			case WT_B:
				return 'B';
			case WT_M:
				return 'M';
			case WT_S:
				return 'S';
			case WT_X:
				return 'X';
		}

		System.out.println("failureWT : " + i);
		System.exit(-1);
		return '!';
	}

	static char TTToChar(int i) {
		switch (i) {
			case TT_D:
				return 'D';
			case TT_R:
				return 'R';
			case TT_P:
				return 'P';
			case TT_A:
				return 'A';
			case TT_F:
				return 'F';
		}
		System.out.println("failure TT: " + i);
		System.exit(-1);
		return '!';
	}


	public static void writeOutput(String fileName, List<Worm> wormsQ) throws IOException {


//		worms = new char[][]{{'B', 'D', 'D', 'F', 'D', 'D', 'F'},
//				{'B', 'D', 'F', 'F', 'D', 'F', 'F'},
//				{'M', 'R', 'F', 'F', 'R', 'R', 'F'},
//				{'M', 'R', 'F', 'F', 'R', 'R', 'F'}};
		char[][] wormsToWrite = new char[wormsQ.size()][N_SHIFTS + 1];

		for (int i = 0; i < workers.size(); i++) {
			wormsToWrite[i][0] = WTToChar(workers.get(i).speciality.ordinal());

			for (int j = 0; j < workers.get(i).previousTasks.length; j++) {
				wormsToWrite[i][j + 1] = TTToChar(workers.get(i).previousTasks[j]);
			}
		}

		StringBuilder outputString = new StringBuilder("");

		PrintWriter pw = new PrintWriter(new FileWriter(fileName));

		for (int wormIndex = 0; wormIndex < wormsToWrite.length; wormIndex++) {
			outputString = new StringBuilder();
			for (int shiftIndex = 0; shiftIndex < wormsToWrite[wormIndex].length; shiftIndex++) {
				outputString.append(wormsToWrite[wormIndex][shiftIndex]);
			}
			pw.println(String.valueOf(outputString));
		}

		pw.close();
	}

	/*
	Boyd Minimum Viable Product
	 */
	public static void mvp(String outputFile) throws IOException {
		int totalWorkers = workers.size();
//		for (int workerCount : workerCounts) {
//			totalWorkers += workerCount;
//		}

//		int[][] workers = new int[totalWorkers][N_SHIFTS + 1];


//		for (int i = 0; i < workers.length; i++) {
//			Arrays.fill(workers[i], -1);
//		}
//		int count = 0;
//		for (int i = 0; i < workerCounts.length; i++) {
//			// The first character of each line is just the Worker type, so add it in
//			// 'i' is the int constant for that Worker Type ()
//			for (int j = 0; j < workerCounts[i]; j++) {
//				workers[count][0] = i;
//				count++;
//			}
//		}

		for (int worm_i = 0; worm_i < workers.size(); worm_i++) {

			if (DEBUG) {
				System.out.println(String.format("Wormid %d / %d", worm_i, workers.size()));
			}// Check if the worm can do a task of it's speciality
			for (int shift = 0; shift < N_SHIFTS; shift++) {
				int worm_type = workers.get(worm_i).speciality.ordinal();

				//TODO: Get rid of tasks
//				System.out.print(N_SHIFTS);
//				System.out.print(" - ");
//				System.out.println(workers.get(worm_i).previousTasks.length);
//				System.out.println();
				if (tasks[worm_type][shift] > 0) {
					workers.get(worm_i).previousTasks[shift] = worm_type;
//					workers[worm_i][shift] = worm_type;
				}
			}
			// Now check again to allocate all the remaining workers
			for (int shift = 0; shift < N_SHIFTS; shift++) {
				int worm_type = workers.get(worm_i).speciality.ordinal();

				for (int work_type = 0; work_type < 4; work_type++) {
					// If a worm hasn't been allocated && there is a task for it to do
					if (workers.get(worm_i).previousTasks[shift] != -1 && tasks[work_type][shift] > 0) {
						workers.get(worm_i).previousTasks[shift] = work_type;
						break;
					}

				}
			}
		}

//		char[][] wormsToWrite = new char[totalWorkers][N_SHIFTS + 1];
//
//		for (int i = 0; i < workers.size(); i++) {
//			wormsToWrite[i][0] = WTToChar(workers.get(i).speciality.ordinal());
//
//			for (int j = 0; j < workers.get(i).previousTasks.length; j++) {
//				wormsToWrite[i][j + 1] = TTToChar(workers.get(i).previousTasks[j]);
//			}
//		}

		writeOutput(outputFile, workers);
	}


}
