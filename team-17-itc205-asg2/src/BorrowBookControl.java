import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {

    private BorrowBookUi ui;
    private Library libraryObj;
    private Member memberObj;
    private enum ControlState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};
    private ControlState stateObj;
    private List<Book> pending;
    private List<Loan> completed;
    private Book bookObj;

    public BorrowBookControl() {
        this.libraryObj = libraryObj.getInstance();
        stateObj = ControlState.INITIALISED;
    }

    public void setUi(BorrowBookUi ui) {
        if (!stateObj.equals(ControlState.INITIALISED)) {
            throw new RuntimeException("BorrowBookControl: cannot call setUi except in INITIALISED state");
        }
        this.ui = ui;
        ui.setState(BorrowBookUi.UiState.READY);
        stateObj = ControlState.READY;
    }

    public void isSwiped(int memberId) {
        if (!stateObj.equals(ControlState.READY)) {
            throw new RuntimeException("BorrowBookControl: cannot call isSwiped except in READY state");
        }
        memberObj = libraryObj.getMember(memberId);
        if (memberObj == null) {
            ui.display("Invalid memberId");
            return;
        }
        if (libraryObj.memberCanBorrow(memberObj)) {
            pending = new ArrayList<>();
            ui.setState(BorrowBookUi.UiState.SCANNING);
            stateObj = ControlState.SCANNING;
        } else {
            ui.display("Member cannot borrow at this time");
            ui.setState(BorrowBookUi.UiState.RESTRICTED);
        }
    }

    public void isScanned(int bookId) {
        bookObj = null;
        if (!stateObj.equals(ControlState.SCANNING)) {
            throw new RuntimeException("BorrowBookControl: cannot call isScanned except in SCANNING state");
        }
        bookObj = libraryObj.getBook(bookId);
        if (bookObj == null) {
            ui.display("Invalid bookId");
            return;
        }
        if (!bookObj.isAvailable()) {
            ui.display("Book cannot be borrowed");
            return;
        }
        pending.add(bookObj);
        for (Book currentBook : pending) {
            String book = currentBook.getBookInfo();
            ui.display(book);
        }
        if (libraryObj.loansRemainingForMember(memberObj) - pending.size() == 0) {
            ui.display("Loan limit reached");
            isComplete();
        }
    }

    public void isComplete() {
        if (pending.size() == 0) {
            cancel();
        } else {
            ui.display("\nFinal Borrowing List");
            for (Book currentBook : pending) {
                String book = currentBook.getBookInfo();
                ui.display(book);
            }
            completed = new ArrayList<Loan>();
            ui.setState(BorrowBookUi.UiState.FINALISING);
            stateObj = ControlState.FINALISING;
        }
    }

    public void commitLoans() {
        if (!stateObj.equals(ControlState.FINALISING)) {
            throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
        }
        for (Book currentBook : pending) {
            Loan loan = libraryObj.issueLoan(currentBook, memberObj);
            completed.add(loan);
        }
        ui.display("Completed Loan Slip");
        for (Loan currentLoan : completed) {
            String loan = currentLoan.toString();
            ui.display(loan);
        }
        ui.setState(BorrowBookUi.UiState.COMPLETED);
        stateObj = ControlState.COMPLETED;
    }

    public void cancel() {
        ui.setState(BorrowBookUi.UiState.CANCELLED);
        stateObj = ControlState.CANCELLED;
    }
}
