package library.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import library.entities.ILoan.LoanState;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class LoanMockTest {

	@Mock
	IPatron patron;
	@Mock
	IBook book;

	@InjectMocks
	Loan loan = new Loan(book, patron);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
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
		verify(patron).takeOutLoan(loan);
		verify(book).borrowFromLibrary();

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
