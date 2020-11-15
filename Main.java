package travelSalesmanProblem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	public static void main(String args[]) throws Exception {
		//Dynamic d = new Dynamic("data/input_int_49_14.txt");
		// Here I followed a strategy from the forum. The path can be divided into two parts based on the points
		// Part 1: point from the 1st city to the 13th city.
		// Part 2: point from the 12th city to the last city.
		// In the two parts, path from the 12th city to the 13th city is counted twice so it needs to be subtracted from the total
		Dynamic d1 = new Dynamic("data/tsp_p1.txt");
		float p1 = d1.get();
		float c12_c13 = d1.C.getDistance(11, 12);
		Dynamic d2 = new Dynamic("data/tsp_p2.txt");
		float p2 = d2.get();
		int out = (int) (p1 + p2 - c12_c13 * 2);
		System.out.println("p1: " + p1 + ", p2: " + p2 + ". Total path: " + out);
	}
	
	public static String bin(int i, int n) {
		int bit = 1;
		String bits = "";
		for(int j = 0; j < n; j++) {
			if((bit&i) >= 1) {
				bits = "1" + bits;
			}
			else {
				bits = "0" + bits;
			}
			bit = bit<<1;
		}
		return bits;
	}
}
