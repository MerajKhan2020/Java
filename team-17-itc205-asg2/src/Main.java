import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private static Scanner input;
    private static Library library;
    private static String menu;
    private static Calendar calendar;
    private static SimpleDateFormat date;

    private static String getMenu() {
        StringBuilder menuStringBuilder = new StringBuilder();
        menuStringBuilder.append("\nLibrary Main Menu\n\n")
                .append("  M  : add member\n")
                .append("  LM : list members\n")
                .append("\n")
                .append("  B  : add book\n")
                .append("  LB : list books\n")
                .append("  FB : fix books\n")
                .append("\n")
                .append("  L  : take out a loan\n")
                .append("  R  : return a loan\n")
                .append("  LL : list loans\n")
                .append("\n")
                .append("  P  : pay fine\n")
                .append("\n")
                .append("  T  : increment date\n")
                .append("  Q  : quit\n")
                .append("\n")
                .append("Choice : ");
        return menuStringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            input = new Scanner(System.in);
            library = Library.getInstance();
            calendar = Calendar.instance();
            date = new SimpleDateFormat("dd/MM/yyyy");
            for (Member member : library.getMembers()) {
                displayOutput(member);
            }
            displayOutput(" ");
            for (Book book : library.getBooks()) {
                displayOutput(book);
            }
            menu = getMenu();
            boolean stopMenuLoop = false;
            while (!stopMenuLoop) {
                Object calendarDate = calendar.getCurrentDate();
                String formattedDate = date.format(calendarDate);
                displayOutput("\n" + formattedDate);
                String choice = getUserInput(menu);
                switch (choice.toUpperCase()) {
                    case "M":
                        addMember();
                        break;
                    case "LM":
                        getMembers();
                        break;
                    case "B":
                        addBook();
                        break;
                    case "LB":
                        getBooks();
                        break;
                    case "FB":
                        fixBooks();
                        break;
                    case "L":
                        borrowBook();
                        break;
                    case "R":
                        returnBook();
                        break;
                    case "LL":
                        getLoans();
                        break;
                    case "P":
                        payFines();
                        break;
                    case "T":
                        incrementDate();
                        break;
                    case "Q":
                        stopMenuLoop = true;
                        break;
                    default:
                        displayOutput("\nInvalid option\n");
                        break;
                }
                Library.save();
            }
        } catch (RuntimeException error) {
            displayOutput(error);
        }
        displayOutput("\nEnded\n");
    }

    private static void payFines() {
        PayFineControl controller = new PayFineControl();
        new PayFineUi(controller).run();
    }

    private static void getLoans() {
        displayOutput("");
        for (Loan loan : library.getCurrentLoans()) {
            displayOutput(loan + "\n");
        }
    }

    private static void getBooks() {
        displayOutput("");
        for (Book book : library.getBooks()) {
            displayOutput(book + "\n");
        }
    }

    private static void getMembers() {
        displayOutput("");
        for (Member member : library.getMembers()) {
            displayOutput(member + "\n");
        }
    }

    private static void borrowBook() {
        BorrowBookControl controller = new BorrowBookControl();
        new BorrowBookUi(controller).run();
    }

    private static void returnBook() {
        ReturnBookControl controller = new ReturnBookControl();
        new ReturnBookUi(controller).run();
    }

    private static void fixBooks() {
        FixBookControl controller = new FixBookControl();
        new FixBookUi(controller).run();
    }

    private static void incrementDate() {
        try {
            String userInput = getUserInput("Enter number of days: ");
            int days = Integer.valueOf(userInput).intValue();
            calendar.incrementDate(days);
            library.checkCurrentLoans();
            Object calendarDate = calendar.getCurrentDate();
            String formattedDate = date.format(calendarDate);
            displayOutput("\n" + formattedDate);
        } catch (NumberFormatException invalidNumber) {
            displayOutput("\nInvalid number of days\n");
        }
    }

    private static void addBook() {
        String author = getUserInput("Enter author: ");
        String title = getUserInput("Enter title: ");
        String callNumber = getUserInput("Enter call number: ");
        Book book = library.addBook(author, title, callNumber);
        displayOutput("\n" + book + "\n");
    }

    private static void addMember() {
        try {
            String lastName = getUserInput("Enter last name: ");
            String firstName = getUserInput("Enter first name: ");
            String email = getUserInput("Enter email: ");
            String userInput = getUserInput("Enter phone number: ");
            int phoneNumber = Integer.valueOf(userInput).intValue();
            Member member = library.addMember(lastName, firstName, email, phoneNumber);
            displayOutput("\n" + member + "\n");
        } catch (NumberFormatException invalidPhoneNumber) {
            displayOutput("\nInvalid phone number\n");
        }
    }

    private static String getUserInput(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    private static void displayOutput(Object object) {
        System.out.println(object);
    }
}
