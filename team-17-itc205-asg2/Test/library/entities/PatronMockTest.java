package library.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import library.entities.IPatron.PatronState;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class PatronMockTest {

	String ln = "T";
	String fn = "J";
	String em = "j@T0";
	long phone = 555L;
	int id = 1;

	@Mock
	ILoan mockLoan;
	@Mock
	ILoan mockLoan1;

	@InjectMocks
	Patron patron = new Patron(ln, fn, em, phone, id);
	PatronState state = PatronState.CAN_BORROW;
	

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
	void testHasOverDueNoLoans() {
		// arrange
		boolean expected = false;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
	}

	@Test
	void testHasOverDueWithCurrentLoans() {
		// arrange
		assertNotNull(mockLoan);
		patron.loans.put(1, mockLoan);

		when(mockLoan.isOverDue()).thenReturn(false);

		boolean expected = false;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
		verify(mockLoan).isOverDue();
	}

	@Test
	void testHasOverDueWithOverdueLoans() {
		// arrange
		assertNotNull(mockLoan);
		patron.loans.put(1, mockLoan);

		when(mockLoan.isOverDue()).thenReturn(true);

		boolean expected = true;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
		verify(mockLoan).isOverDue();
	}

	@Test
	void testHasOverDueWithMultipleLoansNoneOverdue() {
		// arrange
		patron.loans.put(1, mockLoan);
		patron.loans.put(2, mockLoan1);

		when(mockLoan.isOverDue()).thenReturn(false);
		when(mockLoan1.isOverDue()).thenReturn(false);

		boolean expected = false;

		// act
		boolean actual = patron.hasOverDueLoans();

		// assert
		assertEquals(expected, actual);
		verify(mockLoan).isOverDue();
		verify(mockLoan1).isOverDue();
	}


}
