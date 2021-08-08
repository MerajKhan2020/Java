import java.util.Scanner;

public class ReturnBookUi {

    public static enum UiState {INITIALISED, READY, INSPECTING, COMPLETED};
    private ReturnBookControl returnBookControl;
    private Scanner userInput;
    private UiState uiState;

    public ReturnBookUi(ReturnBookControl control) {
        this.returnBookControl = control;
        userInput = new Scanner(System.in);
        uiState = UiState.INITIALISED;
        control.setUi(this);
    }

    public void run() {
        printOutput("Return Book Use Case UI\n");
        while (true) {
            switch (uiState) {
                case INITIALISED:
                    break;
                case READY:
                    String bookInputString = getInput("Scan book (<enter> completes): ");
                    if (bookInputString.length() == 0) {
                        returnBookControl.scanningComplete();
                    } else {
                        try {
                            int bookId = Integer.valueOf(bookInputString).intValue();
                            returnBookControl.bookScanned(bookId);
                        } catch (NumberFormatException invalidBookId) {
                            printOutput("Invalid bookId");
                        }
                    }
                    break;
                case INSPECTING:
                    String answer = getInput("Is book damaged? (Y/N): ");
                    boolean isDamaged = false;
                    if (answer.toUpperCase().equals("Y")) {
                        isDamaged = true;
                    }
                    returnBookControl.dischargeLoan(isDamaged);
                case COMPLETED:
                    printOutput("Return processing complete");
                    return;
                default:
                    printOutput("Unhandled state");
                    throw new RuntimeException("ReturnBookUi : unhandled state :" + uiState);
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return userInput.nextLine();
    }

    private void printOutput(Object printObject) {
        System.out.println(printObject);
    }

    public void display(Object displayObject) {
        printOutput(displayObject);
    }

    public void setState(UiState state) {
        this.uiState = state;
    }
}