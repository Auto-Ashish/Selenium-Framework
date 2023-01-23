package tests;

import org.testng.annotations.Test;

public class Javabasics {

	@Test
	public void printArray() {

		int [] 	ArrayList= {1,2,4,5,2,5,6};
		for(int i :ArrayList) {
			System.out.println(i);
		}
		
		
	}

}
