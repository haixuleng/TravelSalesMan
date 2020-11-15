package travelSalesmanProblem;

//import java.util.ArrayList;
import java.util.HashSet;

public class Subsets {
	int n;
	int k;
	int size;
	HashSet<Integer> bits; // used to generate sets
	HashSet<Integer> hs; // contains all the integer/sets
	public Subsets(int n_size, int k_size) {
		n = n_size;
		k = k_size;
		size = 0;
		bits = new HashSet<Integer>();
		hs = new HashSet<Integer>();
	}
	
	public HashSet<Integer> get() {
		init();
		HashSet<Integer> bits_round0 = new HashSet<Integer>(bits);
		if(k ==1) {
			hs.add(1); // contain the first element only, it is the start of the path
		}
		int k_round = k;
		while(k_round > 1) {
			HashSet<Integer> bits_roundN = new HashSet<Integer>();
			
			for(int bit : bits_round0) {
				for(int ref : bits) {
					int newBit = bit|ref; // add one bit somewhere
					//System.out.println(newBit);
					bits_roundN.add(newBit);
					//System.out.println(bits_roundN.size());
					if(count(newBit) == (k - 1)) {
						hs.add((newBit<<1) + 1);
						//System.out.println("value: " + ((newBit<<1) + 1) + ", bits " + count((newBit<<1) + 1));
					}
				}
			}
			k_round--;
			bits_round0 = new HashSet<Integer>(bits_roundN);
		}
		return hs;
	}
	
	private int count(int i) {
		int bit = 1;
		int cnt = 0;
		for(int j = 0; j <= n-1; j++) {
			if((bit&i) > 0) {
				cnt++;
			}
			bit = bit<<1;
		}
		//System.out.println("value: " + i + ", bits " + cnt);
		return cnt;
	}
	
	private void init() {
		int bit = 1;
		for(int i = 1; i <= n-1; i++) {
			bits.add(bit);
			bit = bit<<1;
		}
	}

}
