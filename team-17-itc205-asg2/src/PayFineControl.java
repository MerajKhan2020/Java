public class PayFineControl {

    private PayFineUi ui;
    private enum ControlState {INITIALISED, READY, PAYING, COMPLETED, CANCELLED};
    private ControlState controlState;
    private Library library;
    private Member member;

    public PayFineControl() {
        this.library = library.getInstance();
        controlState = ControlState.INITIALISED;
    }

    public void setUi(PayFineUi ui) {
        if (!controlState.equals(ControlState.INITIALISED)) {
            throw new RuntimeException("PayFineControl: cannot call setUi except in INITIALISED state");
        }
        this.ui = ui;
        ui.setState(PayFineUi.UiState.READY);
        controlState = ControlState.READY;
    }

    public void cardSwiped(int memberId) {
        if (!controlState.equals(ControlState.READY)) {
            throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
        }
        member = library.getMember(memberId);
        if (member == null) {
            ui.displayObject("Invalid getMember Id");
            return;
        }
        String memberString = member.toString();
        ui.displayObject(memberString);
        ui.setState(PayFineUi.UiState.PAYING);
        controlState = ControlState.PAYING;
    }

    public void cancel() {
        ui.setState(PayFineUi.UiState.CANCELLED);
        controlState = ControlState.CANCELLED;
    }

    public double payFine(double amount) {
        if (!controlState.equals(ControlState.PAYING)) {
            throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
        }
        double change = member.payFine(amount);
        if (change > 0) {
            String formattedChange = String.format("Change: $%.2f", change);
            ui.displayObject(formattedChange);
        }
        String memberString = member.toString();
        ui.displayObject(memberString);
        ui.setState(PayFineUi.UiState.COMPLETED);
        controlState = ControlState.COMPLETED;
        return change;
    }
}