public class FixBookControl {

    private FixBookUi ui;
    private enum ControlState {INITIALISED, READY, FIXING};
    private ControlState controlState;
    private Library library;
    private Book currentBook;

    public FixBookControl() {
        this.library = library.getInstance();
        controlState = ControlState.INITIALISED;
    }

    public void setUi(FixBookUi ui) {
        if (!controlState.equals(ControlState.INITIALISED)) {
            throw new RuntimeException("FixBookControl: cannot call setUi except in INITIALISED state");
        }
        this.ui = ui;
        ui.setState(FixBookUi.UiState.READY);
        controlState = ControlState.READY;
    }

    public void bookScanned(int bookId) {
        if (!controlState.equals(ControlState.READY)) {
            throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
        }
        currentBook = library.getBook(bookId);
        if (currentBook == null) {
            ui.displayObject("Invalid bookId");
            return;
        }
        if (!currentBook.isDamaged()) {
            ui.displayObject("Book has not been damaged");
            return;
        }
        String currentBookString = currentBook.getBookInfo();
        ui.displayObject(currentBookString);
        ui.setState(FixBookUi.UiState.FIXING);
        controlState = ControlState.FIXING;
    }

    public void fixBook(boolean mustFix) {
        if (!controlState.equals(ControlState.FIXING)) {
            throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
        }
        if (mustFix) {
            library.repairBook(currentBook);
        }
        currentBook = null;
        ui.setState(FixBookUi.UiState.READY);
        controlState = ControlState.READY;
    }

    public void scanningComplete() {
        if (!controlState.equals(ControlState.READY)) {
            throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
        }
        ui.setState(FixBookUi.UiState.COMPLETED);
    }
}