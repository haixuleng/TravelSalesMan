package travelSalesmanProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cities {
	int size;
	ArrayList<city> cityList;
	public Cities() {
		cityList = new ArrayList<city>();
		System.out.println("Start to build the cities list");
	}
	
	public void add(city a) {
		//System.out.println(a.x + ", " + a.y);
		cityList.add(a);
		size = cityList.size();
	}
	
	public float getDistance(int i, int j) throws Exception {
		// return the distance between two cities
		if(i > size -1 | j > size -1) {
			throw new Exception("city not in the list yet");
		}
		float dx2 = (cityList.get(i).x - cityList.get(j).x) * (cityList.get(i).x - cityList.get(j).x);
		float dy2 = (cityList.get(i).y - cityList.get(j).y) * (cityList.get(i).y - cityList.get(j).y);
		return (float) Math.sqrt(dx2 + dy2);
	}
	
	public HashSet<HashSet<Integer>> getSubsets(int n, int k){
		// subsets of size k among all integers from 1 to n.
		//ArrayList<ArrayList<Integer>> sets = new ArrayList<ArrayList<Integer>>();
		if(k == 1) {
			HashSet<HashSet<Integer>> aset = new HashSet<HashSet<Integer>>();
			HashSet<Integer> a = new HashSet<Integer>();
			a.add(1);
			aset.add(a);
			return aset;
		}
		else {
			HashSet<HashSet<Integer>> set_k_1 = getSubsets(n, k - 1);
			HashSet<HashSet<Integer>> set_k = new HashSet<HashSet<Integer>>();
			for(HashSet<Integer> eachSet : set_k_1) {
				// loop through each subset of size k - 1, and add one more element to it 
				for(int i = 2; i <= n; i++) {
					if(!eachSet.contains(i)) {
						HashSet<Integer> newSet = new HashSet<Integer>(eachSet);
						newSet.add(i);
						set_k.add(newSet);
						//System.out.println("set size:" + newSet.size());
						//System.out.println("set size:" + eachSet.size());
					}
				}
			}
			return set_k;
		}
	}

}
