package travelSalesmanProblem;

/*
 * this class is used to sort the sets into the size order. Each integer represents a set of integers by the bit
 */

public class BitSet implements Comparable<BitSet> {
	int value;
	int size;
	public BitSet(int v, int s) {
		value = v;
		size = s;
	}
	@Override
	public int compareTo(BitSet o) {
		// TODO Auto-generated method stub
		return Integer.compare(size, o.size);
	}
	
}
