package travelSalesmanProblem;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class Dynamic {
	readIn ri;
	Cities C;
	int A_row;
	int A_col;
	float [][] A; // 2-D array to save the results
	boolean verbose;
	public Dynamic(String path) throws FileNotFoundException {
		ri = new readIn(path);
		C = ri.get();
		A_row = (int)Math.pow(2, C.size);
		A_col = C.size;
		A = new float[A_row][A_col];
		verbose = false;
		init();
	}
	
	public void init() {
		A[0][0] = 0; // where there is only one stop in the path
		A[1][0] = 0; // 00001 is bit representation of only 1 element
		for(int i = 2; i < A_row; i++) {
			A[i][0] = Integer.MAX_VALUE - 99999;
		}
	}
	
	public void loop() throws Exception {
		for(int m = 2; m <= A_col; m++) {
			Subsets sbs = new Subsets(A_col, m);
			HashSet<Integer> sets = sbs.get();
			for(int setOfBits : sets) {
				if(verbose) {
					System.out.println("");
					System.out.println("setOfBits: " + setOfBits + ", A_col: " + A_col + ", A_row:" + A_row);
				}
				int bit = 2; // start from the 2nd bit position
				for(int i = 1; i < A_col; i++) { // i + 1 represents the bit position
					if((bit&setOfBits) > 0) { // pick out the existing bit
						if(verbose) {
							System.out.println("A[" + (setOfBits) + "][" + i + "]:");
						}
						A[setOfBits][i] = findMin(setOfBits, i);
						if(verbose) {
							System.out.println("A[" + (setOfBits) + "][" + i + "]:" + A[setOfBits][i]);
						}
					}
					bit = bit << 1; //left shit one bit
				}
			}
		}
	}
	
	public float findMin(int setOfBits, int i) throws Exception {
		int prevSetOfBits = setOfBits ^ (1<<i); // set the i th bit to 0
		float temp = Integer.MAX_VALUE;
		float current = Integer.MAX_VALUE;
		for(int k = 0; k < A_col; k++) {
			if(k == i | (prevSetOfBits&(1<<(k))) < 1) { // k != i, and k is within subset revSetOfBits
				continue;
			}
			else {
				current = (float) A[prevSetOfBits][k] + C.getDistance(i, k);
				if(verbose) {
					System.out.println("prevBit: " + prevSetOfBits + ", distance between " + (i) + " and " + (k) + ": " + C.getDistance(i, k));
					System.out.println("A[" + prevSetOfBits + "][" + k + "]:" + A[prevSetOfBits][k]);
				}
				if(current < temp) {
					temp = current;
				}
			}
		}
		return temp;
	}
	
	public float findFinal(int setOfBits, int i) throws Exception {
		int prevSetOfBits = setOfBits; // set the i th bit to 0
		float temp = Integer.MAX_VALUE;
		float current = Integer.MAX_VALUE;
		for(int k = 1; k < A_col; k++) {
			if((prevSetOfBits&(1<<(k))) < 1) { // k != i, and k is within subset revSetOfBits
				continue;
			}
			else {
				current = (float) A[prevSetOfBits][k] + C.getDistance(i, k);
				System.out.println("prevBit: " + prevSetOfBits + ", distance between " + (i) + " and " + (k) + ": " + C.getDistance(i, k));
				System.out.println("A[" + prevSetOfBits + "][" + k + "]:" + A[prevSetOfBits][k]);
				if(current < temp) {
					temp = current;
				}
			}
		}
		return temp;
	}
	
	public float get() throws Exception {
		loop();
		return findFinal((int) Math.pow(2, A_col) - 1, 0); // 2^n - 2 will return 1111...110, findmin will make it to 11111...111, then find the minimum
	}

}
