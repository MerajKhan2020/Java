package library.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPatronLowLevelIntegration {

	ILoan loan;
	ILoan loan1;
	IBook book;

	Patron patron;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		patron = new Patron("T", "J", "j@T0", 555L, 1);
		book = new Book("A", "T", "C", 1);

		loan = new Loan(book, patron);
		loan1 = new Loan(book, patron);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHasOverDueWithCurrentLoans() {
		// arrange
		patron.loans.put(1, loan);

		loan.isOverDue();

		boolean expected = false;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
	}

	@Test
	void testHasOverDueWithMultipleLoansNoneOverdue() {
		// arrange
		patron.loans.put(1, loan);
		patron.loans.put(2, loan1);

		loan.isOverDue();
		loan1.isOverDue();

		boolean expected = false;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
	}

}
