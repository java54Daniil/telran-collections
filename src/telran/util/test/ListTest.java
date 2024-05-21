package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.List;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>) collection;
	}

	@Test

	void GetTest() {
		assertEquals(-20, list.get(0));
		assertEquals(10, list.get(1));
		assertEquals(1, list.get(2));
		assertEquals(100, list.get(3));
		assertEquals(-5, list.get(4));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(numbers.length));
	}

	@Test

	void AddWithIndexTest() {
		list.add(0, 50);
		assertEquals(50, list.get(0));
		list.add(2, 200);
		assertEquals(200, list.get(2));
		list.add(list.size(), 500);
		assertEquals(500, list.get(list.size() - 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 50));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 50));
	}

	@Test

	void RemoveWithIndexTest() {
		assertEquals(-20, list.remove(0));
		assertEquals(1, list.remove(1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
	}

	@Test

	void IndexOfTest() {
		assertEquals(0, list.indexOf(-20));
		assertEquals(2, list.indexOf(1));
		assertEquals(4, list.indexOf(-5));
		assertEquals(-1, list.indexOf(500));
	}

	@Test

	void LastIndexOfTest() {
		collection.add(1);
		assertEquals(0, list.lastIndexOf(-20));
		assertEquals(5, list.lastIndexOf(1));
		assertEquals(4, list.lastIndexOf(-5));
		assertEquals(-1, list.lastIndexOf(500));
	}
}
