
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {

    private static final String LIBRARY_FILE = "library.obj";
    private static final int LOAN_LIMIT = 2;
    private static final int LOAN_PERIOD = 2;
    private static final double FINE_PER_DAY = 1.0;
    private static final double MAX_FINES_OWED = 1.0;
    private static final double DAMAGE_FEE = 2.0;
    private static Library self;
    private int bookId;
    private int memberId;
    private int loanId;
    private Date loanDate;
    private Map<Integer, Book> catalog;
    private Map<Integer, Member> members;
    private Map<Integer, Loan> loans;
    private Map<Integer, Loan> currentLoans;
    private Map<Integer, Book> damagedBooks;

    private Library() {
        catalog = new HashMap<>();
        members = new HashMap<>();
        loans = new HashMap<>();
        currentLoans = new HashMap<>();
        damagedBooks = new HashMap<>();
        bookId = 1;
        memberId = 1;
        loanId = 1;
    }

    public static synchronized Library getInstance() {
        if (self == null) {
            Path path = Paths.get(LIBRARY_FILE);
            if (Files.exists(path)) {
                try (FileInputStream fileInputStream = new FileInputStream(LIBRARY_FILE);
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                    self = (Library) objectInputStream.readObject();
                    Calendar.instance().setDate(self.loanDate);
                    objectInputStream.close();
                } catch (Exception readError) {
                    throw new RuntimeException(readError);
                }
            } else {
                self = new Library();
            }
        }
        return self;
    }

    public static synchronized void save() {
        if (self != null) {
            self.loanDate = Calendar.instance().getCurrentDate();
            try (FileOutputStream fileOutputStream = new FileOutputStream(LIBRARY_FILE);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
                objectOutputStream.writeObject(self);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (Exception writeError) {
                throw new RuntimeException(writeError);
            }
        }
    }

    public int bookID() {
        return bookId;
    }

    public int memberID() {
        return memberId;
    }

    private int nextBookId() {
        return bookId++;
    }

    private int nextMemberId() {
        return memberId++;
    }

    private int nextLoanId() {
        return loanId++;
    }

    public List<Member> getMembers() {
        return new ArrayList<Member>(members.values());
    }

    public List<Book> getBooks() {
        return new ArrayList<Book>(catalog.values());
    }

    public List<Loan> getCurrentLoans() {
        return new ArrayList<Loan>(currentLoans.values());
    }

    public Member addMember(String lastName, String firstName, String email, int phoneNo) {
        int nextMemberId = nextMemberId();
        Member newMember = new Member(lastName, firstName, email, phoneNo, nextMemberId);
        int newMemberId = newMember.getMemberId();
        members.put(newMemberId, newMember);
        return newMember;
    }

    public Book addBook(String author, String title, String callNumber) {
        int nextBookId = nextBookId();
        Book newBook = new Book(author, title, callNumber, nextBookId);
        int newBookId = newBook.getId();
        catalog.put(newBookId, newBook);
        return newBook;
    }

    public Member getMember(int memberId) {
        if (members.containsKey(memberId)) {
            return members.get(memberId);
        }
        return null;
    }

    public Book getBook(int bookId) {
        if (catalog.containsKey(bookId)) {
            return catalog.get(bookId);
        }
        return null;
    }

    public int loanLimit() {
        return LOAN_LIMIT;
    }

    public boolean memberCanBorrow(Member member) {
        int numberOfCurrentLoan = member.getNumberOfCurrentLoans();
        if (numberOfCurrentLoan == LOAN_LIMIT) {
            return false;
        }
        double finesOwned = member.getFinesOwed();
        if (finesOwned >= MAX_FINES_OWED) {
            return false;
        }
        for (Loan loan : member.getMemberLoans()) {
            if (loan.isOverDue()) {
                return false;
            }
        }
        return true;
    }

    public int loansRemainingForMember(Member member) {
        int loansRemaining = LOAN_LIMIT - member.getNumberOfCurrentLoans();
        return loansRemaining;
    }

    public Loan issueLoan(Book book, Member member) {
        Date dueDate = Calendar.instance().getDueDate(LOAN_PERIOD);
        int nextLoanId = nextLoanId();
        Loan loan = new Loan(nextLoanId, book, member, dueDate);
        member.takeOutLoan(loan);
        book.borrowBook();
        int loanId = loan.getId();
        loans.put(loanId, loan);
        int bookId = book.getId();
        currentLoans.put(bookId, loan);
        return loan;
    }

    public Loan loanByBookId(int bookId) {
        if (currentLoans.containsKey(bookId)) {
            return currentLoans.get(bookId);
        }
        return null;
    }

    public double calculateOverDueFine(Loan loan) {
        if (loan.isOverDue()) {
            long daysOverDue = Calendar.instance().getDaysDifference(loan.getDueDate());
            double fine = daysOverDue * FINE_PER_DAY;
            return fine;
        }
        return 0.0;
    }

    public void dischargeLoan(Loan currentLoan, boolean isDamaged) {
        Member member = currentLoan.getMember();
        Book book = currentLoan.getBook();
        double overDueFine = calculateOverDueFine(currentLoan);
        member.addFine(overDueFine);
        member.dischargeLoan(currentLoan);
        book.returnBook(isDamaged);
        if (isDamaged) {
            member.addFine(DAMAGE_FEE);
            int bookId = book.getId();
            damagedBooks.put(bookId, book);
        }
        currentLoan.setDischarged();
        currentLoans.remove(book.getId());
    }

    public void checkCurrentLoans() {
        for (Loan loan : currentLoans.values()) {
            loan.checkOverDue();
        }
    }

    public void repairBook(Book currentBook) {
        if (damagedBooks.containsKey(currentBook.getId())) {
            currentBook.repairBook();
            damagedBooks.remove(currentBook.getId());
        } else {
            throw new RuntimeException("Library: repairBook: book is not damaged");
        }
    }
}
