import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

    private String title;
    private String author;
    private String callNo;
    private int id;
    private enum State {AVAILABLE, ON_LOAN, DAMAGED, RESERVED};
    private State state;

    public Book(String author, String title, String callNo, int id) {
        this.author = author;
        this.title = title;
        this.callNo = callNo;
        this.id = id;
        this.state = State.AVAILABLE;
    }

    public String getBookInfo() {
        StringBuilder bookInfo = new StringBuilder();
        bookInfo.append("Book: ").append(id).append("\n")
                .append("  Title:  ").append(title).append("\n")
                .append("  Author: ").append(author).append("\n")
                .append("  CallNo: ").append(callNo).append("\n")
                .append("  state:  ").append(state);
        return bookInfo.toString();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return state == State.AVAILABLE;
    }

    public boolean isOnLoan() {
        return state == State.ON_LOAN;
    }

    public boolean isDamaged() {
        return state == State.DAMAGED;
    }

    public void borrowBook() {
        if (state.equals(State.AVAILABLE)) {
            state = State.ON_LOAN;
        } else {
            String borrowError = String.format("Book: cannot borrow while Book is in state: %s", state);
            throw new RuntimeException(borrowError);
        }
    }

    public void returnBook(boolean damaged) {
        if (state.equals(State.ON_LOAN)) {
            if (damaged) {
                state = State.DAMAGED;
            } else {
                state = State.AVAILABLE;
            }
        } else {
            String returnError = String.format("Book: cannot return while Book is in state: %s", state);
            throw new RuntimeException(returnError);
        }
    }

    public void repairBook() {
        if (state.equals(State.DAMAGED)) {
            state = State.AVAILABLE;
        } else {
            String repairError = String.format("Book: cannot repair while Book is in state: %s", state);
            throw new RuntimeException(repairError);
        }
    }
}