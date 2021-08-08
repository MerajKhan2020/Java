public class ReturnBookControl {

    private ReturnBookUi ui;
    private enum ControlState {INITIALISED, READY, INSPECTING};
    private ControlState controlState;
    private Library library;
    private Loan currentLoan;

    public ReturnBookControl() {
        this.library = library.getInstance();
        controlState = ControlState.INITIALISED;
    }

    public void setUi(ReturnBookUi ui) {
        if (!controlState.equals(ControlState.INITIALISED)) {
            throw new RuntimeException("ReturnBookControl: cannot call setUi except in INITIALISED state");
        }
        this.ui = ui;
        ui.setState(ReturnBookUi.UiState.READY);
        controlState = ControlState.READY;
    }

    public void bookScanned(int bookId) {
        if (!controlState.equals(ControlState.READY)) {
            throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
        }
        Book currentBook = library.getBook(bookId);
        if (currentBook == null) {
            ui.display("Invalid bookId");
            return;
        }
        if (!currentBook.isOnLoan()) {
            ui.display("Book has not been borrowed");
            return;
        }
        currentLoan = library.loanByBookId(bookId);
        double overDueFine = 0.0;
        if (currentLoan.isOverDue()) {
            overDueFine = library.calculateOverDueFine(currentLoan);
        }
        ui.display("Inspecting");
        String book = currentBook.getBookInfo();
        String loan = currentLoan.getLoanInfo();
        ui.display(book);
        ui.display(loan);
        if (currentLoan.isOverDue()) {
            String fine = String.format("\nOverdue fine : $%.2f", overDueFine);
            ui.display(fine);
        }
        ui.setState(ReturnBookUi.UiState.INSPECTING);
        controlState = ControlState.INSPECTING;
    }

    public void scanningComplete() {
        if (!controlState.equals(ControlState.READY)) {
            throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
        }
        ui.setState(ReturnBookUi.UiState.COMPLETED);
    }

    public void dischargeLoan(boolean isDamaged) {
        if (!controlState.equals(ControlState.INSPECTING)) {
            throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
        }
        library.dischargeLoan(currentLoan, isDamaged);
        currentLoan = null;
        ui.setState(ReturnBookUi.UiState.READY);
        controlState = ControlState.READY;
    }
}