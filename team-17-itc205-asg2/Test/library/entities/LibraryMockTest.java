package library.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.quality.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import library.entities.helpers.IBookHelper;
import library.entities.helpers.ILoanHelper;
import library.entities.helpers.IPatronHelper;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LibraryMockTest {

	@Mock
	IBookHelper mockBookHelper;
	@Mock
	IPatronHelper mockPatronHelper;
	@Mock
	ILoanHelper mockLoanHelper;

	@Mock
	IPatron mockPatron;
	@Mock
	ILoan mockLoan;
	@Mock
	IBook mockBook;

	@InjectMocks
	Library library = new Library(mockBookHelper, mockPatronHelper, mockLoanHelper);

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
	void testLibraryAllParametersOK() {
		// arrange
		String ln = "Smith";
		String fn = "John";
		String email = "Jsmith@live.com";
		long phone = 6421L;
		int id = 1;

		when(mockPatronHelper.makePatron(ln, fn, email, phone, id)).thenReturn(mockPatron);

		// act
		IPatron patron = library.addPatron(ln, fn, email, phone);

		// assert
		assertNotNull(patron);
		List<IPatron> pList = library.getPatronList();
		assertTrue(pList.size() == 1);

	}

	@Test
	void testCommitLoan() {
		// arrange
		int bookId = 1;

		when(mockLoan.getBook()).thenReturn(mockBook);
		when(mockBook.getId()).thenReturn(bookId);
		when(mockLoan.getPatron()).thenReturn(mockPatron);

		// act
		library.commitLoan(mockLoan);

		// assert
		verify(mockLoan).commit(anyInt(), any());
		ILoan loan = library.getCurrentLoanByBookId(bookId);
		assertEquals(loan, mockLoan);

	}

	@Test
	void testIssueLoanBookNotAvailable() {
		// arrange
		assertNotNull(mockBook);
		assertNotNull(mockPatron);
		when(mockBook.isAvailable()).thenReturn(false);

		// act
		Executable e = () -> library.issueLoan(mockBook, mockPatron);

		// assert
		assertThrows(RuntimeException.class, e);
	}

	@Test
	void testIssueLoanBookNotAvailablePatronCannotBorrow() {
		// arrange
		assertNotNull(mockBook);
		assertNotNull(mockPatron);
		when(mockBook.isAvailable()).thenReturn(false);
		when(mockPatron.getNumberOfCurrentLoans()).thenReturn(2);

		// act
		Executable e = () -> library.issueLoan(mockBook, mockPatron);

		// assert
		assertThrows(RuntimeException.class, e);
	}

	@Test
	void testPatronCanBorrowNoOverDuUnderBorrowMaxUnderFineMax() {
		// arrange
		when(mockPatron.getNumberOfCurrentLoans()).thenReturn(0);
		when(mockPatron.getFinesPayable()).thenReturn(0.0);
		when(mockPatron.hasOverDueLoans()).thenReturn(false);

		// act
		boolean actual = library.patronCanBorrow(mockPatron);

		// assert
		assertTrue(actual);
	}

}
