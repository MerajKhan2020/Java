package library.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLoanLowLevelIntegration {

	IPatron patron;
	IBook book;

	Loan loan;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		patron = new Patron("LN", "FN", "EM", 999L, 1);
		book = new Book("A", "T", "C", 1);

		loan = new Loan(book, patron);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCommit() {
		// arrange
		int loanId = 1;
		Date date = new Date();

		// act
		loan.commit(loanId, date);

		// assert
		assertTrue(loan.isCurrent());
		assertEquals(1, patron.getNumberOfCurrentLoans());
		assertTrue(book.isOnLoan());
	}

	@Test
	void testCheckOverDueDate() {
		// arrange
		int loanId = 1;
		Date dueDate = new Date(12 / 12 / 2017);
		Date currentDate = new Date();

		loan.commit(loanId, dueDate);
		boolean expected = true;

		// act
		boolean actual = loan.checkOverDue(currentDate);

		// assert
		assertEquals(expected, actual);
		assertTrue(loan.isOverDue());

	}

}
