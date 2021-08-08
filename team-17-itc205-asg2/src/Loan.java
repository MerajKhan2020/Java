import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {

    public static enum LoanState {CURRENT, OVER_DUE, DISCHARGED};
    private int loanId;
    private Book book;
    private Member member;
    private Date dueDate;
    private LoanState state;

    public Loan(int loanId, Book book, Member member, Date dueDate) {
        this.loanId = loanId;
        this.book = book;
        this.member = member;
        this.dueDate = dueDate;
        this.state = LoanState.CURRENT;
    }

    public void checkOverDue() {
        if (state == LoanState.CURRENT &&
                Calendar.instance().getCurrentDate().after(dueDate)) {
            this.state = LoanState.OVER_DUE;
        }
    }

    public boolean isOverDue() {
        return state == LoanState.OVER_DUE;
    }

    public Integer getId() {
        return loanId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getLoanInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDueDate = dateFormat.format(dueDate);
        int memberId = member.getMemberId();
        String memberSurname = member.getLastName();
        String memberFirstName = member.getFirstName();
        int bookId = book.getId();
        String bookTitle = book.getTitle();
        StringBuilder loanInfo = new StringBuilder();
        loanInfo.append("Loan:  ").append(loanId).append("\n")
                .append("  Borrower ").append(memberId).append(" : ")
                .append(memberSurname).append(", ").append(memberFirstName).append("\n")
                .append("  getBook ").append(bookId).append(" : ")
                .append(bookTitle).append("\n")
                .append("  DueDate: ").append(formattedDueDate).append("\n")
                .append("  State: ").append(state);
        return loanInfo.toString();
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public void setDischarged() {
        state = LoanState.DISCHARGED;
    }
}
