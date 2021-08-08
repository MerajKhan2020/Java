import java.util.Scanner;

public class BorrowBookUi {

    public static enum UiState {INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED};
    private BorrowBookControl controlObj;
    private Scanner currentInput;
    private UiState currentState;

    public BorrowBookUi(BorrowBookControl control) {
        this.controlObj = control;
        currentInput = new Scanner(System.in);
        currentState = UiState.INITIALISED;
        control.setUi(this);
    }

    private String processInput(String prompt) {
        System.out.print(prompt);
        return currentInput.nextLine();
    }

    private void processOutput(Object outputObject) {
        System.out.println(outputObject);
    }

    public void setState(UiState state) {
        this.currentState = state;
    }

    public void run() {
        processOutput("Borrow Book Use Case UI\n");
        while (true) {
            switch (currentState) {
                case CANCELLED:
                    processOutput("Borrowing Cancelled");
                    return;
                case READY:
                    String memberCard = processInput("Swipe member card (press <enter> to cancel): ");
                    if (memberCard.length() == 0) {
                        controlObj.cancel();
                        break;
                    }
                    try {
                        int memberId = Integer.valueOf(memberCard).intValue();
                        controlObj.isSwiped(memberId);
                    } catch (NumberFormatException invalidMemberId) {
                        processOutput("Invalid memberId");
                    }
                    break;
                case RESTRICTED:
                    processInput("Press <any key> to cancel");
                    controlObj.cancel();
                    break;
                case SCANNING:
                    String bookCode = processInput("Scan book (<enter> completes): ");
                    if (bookCode.length() == 0) {
                        controlObj.isComplete();
                        break;
                    }
                    try {
                        int bookId = Integer.valueOf(bookCode).intValue();
                        controlObj.isScanned(bookId);
                    } catch (NumberFormatException invalidBookId) {
                        processOutput("Invalid bookId");
                    }
                    break;
                case FINALISING:
                    String answer = processInput("Commit loans? (Y/N): ");
                    if (answer.toUpperCase().equals("N")) {
                        controlObj.cancel();
                    } else {
                        controlObj.commitLoans();
                        processInput("Press <any key> to complete ");
                    }
                    break;
                case COMPLETED:
                    processOutput("Borrowing Completed");
                    return;
                default:
                    processOutput("Unhandled state");
                    throw new RuntimeException("BorrowBookUi : unhandled state :" + currentState);
            }
        }
    }

    public void display(Object displayObject) {
        processOutput(displayObject);
    }
}