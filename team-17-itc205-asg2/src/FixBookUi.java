import java.util.Scanner;

public class FixBookUi {

    public static enum UiState {INITIALISED, READY, FIXING, COMPLETED};
    private FixBookControl fixBookControl;
    private Scanner keyboardInput;
    private UiState uiState;

    public FixBookUi(FixBookControl fixBookControl) {
        this.fixBookControl = fixBookControl;
        keyboardInput = new Scanner(System.in);
        uiState = UiState.INITIALISED;
        fixBookControl.setUi(this);
    }

    public void setState(UiState state) {
        this.uiState = state;
    }

    public void run() {
        printObject("Fix Book Use Case UI\n");
        while (true) {
            switch (uiState) {
                case READY:
                    String bookString = getInput("Scan book (<enter> completes): ");
                    if (bookString.length() == 0) {
                        fixBookControl.scanningComplete();
                    } else {
                        try {
                            int bookId = Integer.valueOf(bookString).intValue();
                            fixBookControl.bookScanned(bookId);
                        } catch (NumberFormatException invalidBookId) {
                            printObject("Invalid bookId");
                        }
                    }
                    break;
                case FIXING:
                    String fixAnswer = getInput("Fix book? (Y/N) : ");
                    boolean fix = false;
                    if (fixAnswer.toUpperCase().equals("Y")) {
                        fix = true;
                    }
                    fixBookControl.fixBook(fix);
                    break;
                case COMPLETED:
                    printObject("Fixing process complete");
                    return;
                default:
                    printObject("Unhandled state");
                    throw new RuntimeException("FixBookUi : unhandled state :" + uiState);
            }
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return keyboardInput.nextLine();
    }

    private void printObject(Object printObject) {
        System.out.println(printObject);
    }

    public void displayObject(Object displayObject) {
        printObject(displayObject);
    }
}