package telran.util.test;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
	Collection<Integer> collection;
	Integer[] numbers = {-20,10,1,100,-5};
	@BeforeEach
	void setUp() {
		for(Integer num :numbers) {
			collection.add(num);
		}
	}
	@Test
		void iterratorTest() {
			runTest(numbers);
		}
		protected  void runTest(Integer[] expected) {
			Integer[] actual = collection.stream().toArray(Integer[]::new);
			assertArrayEquals(expected, actual);
		};
		
	    @Test
	    void AddTest() {
			assertTrue(collection.add(50));
	        assertTrue(collection.contains(50));
	    }

	    @Test
	    void RemoveTest() {
	        assertTrue(collection.remove(-20));
	        assertFalse(collection.contains(-20));
	    }

	    @Test
	    void ContainsTest() {
	        assertTrue(collection.contains(1));
	        assertFalse(collection.contains(500));
	    }

	    @Test
	    void SizeTest() {
	        assertEquals(numbers.length, collection.size());
	        collection.add(50);
	        assertEquals(numbers.length + 1, collection.size());
	        collection.remove(-20);
	        assertEquals(numbers.length, collection.size());
	    }
	}

