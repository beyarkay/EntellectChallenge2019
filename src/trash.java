import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class trash {
	
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
	
	public static void main(String[] args) throws IOException {
		readInput("input/map_5.input");
		
		int nF = 14;
		
		PrintWriter pr = new PrintWriter(new FileWriter("output/map_5.output"));
		
		for (int j = 0; j < workerCounts[0]; j++) {
			pr.print("B");
			for (int i = 0; i < 1; i++)
				pr.print("");
			pr.print("DFFFFFFFFD");
			pr.println();
		}
		
		for (int j = 0; j < workerCounts[1]; j++) {
			pr.print("M");
			for (int i = 0; i < 1; i++)
				pr.print("");
			pr.print("RFFFFFFFFR");
			pr.println();
		}
		
		for (int j = 0; j < workerCounts[2]; j++) {
			pr.print("S");
			for (int i = 0; i < 1; i++)
				pr.print("");
			pr.print("PFFFFFFFFP");
			pr.println();
		}
		
		for (int j = 0; j < workerCounts[3]; j++) {
			pr.print("X");
			for (int i = 0; i < 1; i++)
				pr.print("");
			pr.print("AFFFFFFFA");
			pr.println();
		}
		
		pr.close();
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
}
