import java.util.Scanner;

public class PayFineUi {

    public static enum UiState {INITIALISED, READY, PAYING, COMPLETED, CANCELLED};
    private PayFineControl payFineControl;
    private Scanner keyboardInput;
    private UiState uiState;

    public PayFineUi(PayFineControl payFineControl) {
        this.payFineControl = payFineControl;
        keyboardInput = new Scanner(System.in);
        uiState = UiState.INITIALISED;
        payFineControl.setUi(this);
    }

    public void setState(UiState uiState) {
        this.uiState = uiState;
    }

    public void run() {
        printObject("Pay Fine Use Case UI\n");
        while (true) {
            switch (uiState) {
                case READY:
                    String memberString = getInput("Swipe member card (press <enter> to cancel): ");
                    if (memberString.length() == 0) {
                        payFineControl.cancel();
                        break;
                    }
                    try {
                        int memberId = Integer.valueOf(memberString).intValue();
                        payFineControl.cardSwiped(memberId);
                    } catch (NumberFormatException invalidMemberId) {
                        printObject("Invalid memberId");
                    }
                    break;
                case PAYING:
                    double amount = 0;
                    String amountString = getInput("Enter amount (<Enter> cancels) : ");
                    if (amountString.length() == 0) {
                        payFineControl.cancel();
                        break;
                    }
                    try {
                        amount = Double.valueOf(amountString).doubleValue();
                    } catch (NumberFormatException invalidNumber) {
                    }
                    if (amount <= 0) {
                        printObject("Amount must be positive");
                        break;
                    }
                    payFineControl.payFine(amount);
                    break;
                case CANCELLED:
                    printObject("Pay Fine process cancelled");
                    return;
                case COMPLETED:
                    printObject("Pay Fine process complete");
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