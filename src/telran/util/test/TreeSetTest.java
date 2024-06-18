package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeSet;


public class TreeSetTest extends SortedSetTest {
	
	TreeSet<Integer> treeSet;
	@Override
	@BeforeEach
	void setUp() {
		collection = new TreeSet<>(); 
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
	}
	@Test
	void displayRootChildrenTest() {
		System.out.println("displayRootChildren");
		treeSet.displayRootChildren();
	}
	@Test
	void treeInversionTest() {
		treeSet.treeInversion();
		Integer[] expected = {100, 10, 1, -5,  -20};
		Integer[] actual = new Integer[treeSet.size()];
		int index = 0;
		for(Integer num: treeSet) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertTrue(treeSet.contains(100));
	}
	@Test
	void displayTreeRotatedTest() {
		System.out.println("displayTreeRotatedTest");
		treeSet.setSpacesPerLevel(4);
		treeSet.displayTreeRotated();
	}
	@Test
	void widthTest() {
		assertEquals(2, treeSet.width());
	}
	@Test
	void heightTest() {
		assertEquals(4, treeSet.heigth());
	}
	@Test
	void sortedSequenceTreeTest() {
		TreeSet<Integer> treeSet = new TreeSet<>();
		int[] sortedArray = new Random().ints().distinct()
				.limit(N_ELEMENTS).sorted().toArray();
		transformArray(sortedArray);
		for(int num:sortedArray) {
			treeSet.add(num);
		}
		
		balancedTreeTest(treeSet);
		
	}
	private void transformArray(int[] sortedArray) {
		if(sortedArray !=null) {
		  int[] tempArray = new int[sortedArray.length];
		  transformArray(sortedArray, tempArray, 0, sortedArray.length - 1, 0);
		  System.arraycopy(tempArray, 0, sortedArray, 0, sortedArray.length);
		}
	}
	private int transformArray(int[] sortedArray, int[] tempArray, int left, int right, int index) {
		if (left <= right) {
	        int mid = (left + right) / 2;
	        tempArray[index] = sortedArray[mid];
	        index++;
	        index = transformArray(sortedArray, tempArray, left, mid - 1, index);
	        index = transformArray(sortedArray, tempArray, mid + 1, right, index);
	    }
	    return index;
	}
	private void balancedTreeTest(TreeSet<Integer> treeSet) {
		assertEquals(20, treeSet.heigth());
		assertEquals((N_ELEMENTS+1)/2,treeSet.width() );
	}
	
	@Test
	void balanceTreeTest() {
		createBigRandomCollection(new Random());
		treeSet.balance();
		balancedTreeTest(treeSet);
		int index = 0;
		for(Integer num: treeSet) {
			index++;
		}
		assertEquals(treeSet.size(), index);
		
	}
}
