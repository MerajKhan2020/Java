package library.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookUnitTest {

	Book sut;
	String author = "A";
	String title = "B";
	String callNo = "C1";
	int bookId = 1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sut = new Book(author, title, callNo, bookId);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIsAvailableAfterConstruction() {
		// arrange
		boolean expected = true;

		// act
		boolean actual = sut.isAvailable();

		// asserts
		assertTrue(expected == actual);
	}

	@Test
	void testIsAvailableWhenOnLoan() {
		// arrange
		sut.borrowFromLibrary();
		assertTrue(sut.getState() == IBook.BookState.ON_LOAN);
		boolean expected = false;

		// act
		boolean actual = sut.isAvailable();

		// asserts
		assertTrue(expected == actual);
	}

	@Test
	void testIsAvailableWhenDamaged() {
		// arrange
		sut.borrowFromLibrary();
		sut.returnToLibrary(true);
		boolean expected = false;

		// act
		boolean actual = sut.isAvailable();

		// asserts
		assertTrue(expected == actual);
	}
}
