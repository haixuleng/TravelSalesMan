package travelSalesmanProblem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readIn {
	String fileToLoad;
	int length = 0;
	// First line of the file stores the number of vertexes from the test file
	public readIn(String fileName) throws FileNotFoundException {
		fileToLoad = fileName;
		System.out.println(fileName);
		size();
	}

	public void size() throws FileNotFoundException {
		int size = 0;
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		while(myReader.hasNextLine()) {
			size ++;
			myReader.nextLine();
			//System.out.println("Length of Input: " + size);
		}
		//myReader.close();
		length = size;
		System.out.println("Length of Input: " + size);
	}

	public Cities get() throws FileNotFoundException {
		File myObj = new File(fileToLoad);
		Scanner myReader = new Scanner(myObj);
		Cities initData = new Cities();
		int index = 0;
		while(myReader.hasNextLine()) {
			String data = myReader.nextLine().strip();
			if(index < 1) {
				index ++;
				continue; // starting line
			}
			String[] values = data.split("\\s");
			city c = new city(Float.parseFloat(values[0]), Float.parseFloat(values[1]));
			initData.add(c);
		}
		myReader.close();
		return initData;
	}
}
