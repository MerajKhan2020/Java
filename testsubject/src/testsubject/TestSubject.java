package testsubject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//Note:   
public class TestSubject {
    public static String INPUT_FILE_NAME = "SubjectList.txt";

    private int initialSubjectSize = 0;
    private List<Subject> subjectList;

    private void initialize() {
        subjectList = Utils.readSubjects(INPUT_FILE_NAME);
        initialSubjectSize = subjectList.size();
    }

    //Note:   
    public void run() {
        initialize(); //Initiates file reading process by invoking readSubjects method from Utils class.  

        printSubjects(); //displays the subjects names and corresponding Subject code in console

        userInteraction(); //this method controls the user input and varifies it . 

        writeNewSubjects(); //after receiving input from previous step, this method invokes writeSubjects method from Utils class and passes the value to  printSubjects method.

        printSubjects(); // this method outputs final values to text file. 
    }

    //Note: Prints Subject list  
    private void printSubjects() {
        for(int i = 0; i < subjectList.size(); i++) {
            System.out.println(subjectList.get(i));
        }
    }

    //Note: method prompts user to start process of adding new subject
    private void userInteraction() {
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to add a new subject? 0 = no, 1 = yes");

            String strAddNew = console.next().trim();

            if("1".equals(strAddNew)) {
                Subject newSubject = inputOneSubject(console);

                if(newSubject != null) {
                    subjectList.add(newSubject);
                }
            } else if ("0".equals(strAddNew)){
                break;
            } else {
                System.out.println("invalid input, enter 0 or 1");
            }
        }
    }

    //Note:   prompts user to input subject decipline and code
    private Subject inputOneSubject(Scanner console) {
        System.out.println("All discipline areas: ");

        printDiscipline();

        System.out.println("Enter discipline code:");

        String disciplineCode = console.next(); 

        String[] codesPerDiscipline = Subject.codesPerDiscipline(toSubjectArray(), disciplineCode.toUpperCase());

        printSubjectCodes(codesPerDiscipline);

        System.out.println("Enter 3 digit code:");

        String subjectNumber = console.next(); 
        console.nextLine();

        String enteredSubjectCode = (disciplineCode + subjectNumber).toUpperCase();

        if(! Subject.codeExists(toSubjectArray(), enteredSubjectCode)) {
            System.out.println("New Code: " + enteredSubjectCode);
        
            if (Subject.ValidCode(enteredSubjectCode)){
               
                               
                System.out.println("Enter new subject name:");
                String subjectName = console.nextLine();
                System.out.println("Subject code unique");
                return new Subject(subjectName, enteredSubjectCode);
            }
            
            else{
                System.out.println("Invalid Subject Code");
                return null;
            }

            
        } else {
            System.out.println("Subject code exists");
            return null;
        }
    }

    //Note: prints an array containing the different subject codes represented in the array of subjects for the particular discipline  
    private void printSubjectCodes(String[] codesPerDiscipline) {
        printArray(codesPerDiscipline);
    }

    //Note: Prints an array containing the different 3-character discipline codes represented in the array of subjects
    //in alphabetically order 
    private void printDiscipline() {
        String[] disciplines = Subject.allDisciplines(toSubjectArray());
        printArray(disciplines);
    }

    //Note: method to print arrays used in other methods
    private void printArray(String[] items) {
        for(int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    //Note: Array containting values from user input  
    private Subject[] toSubjectArray() {
        return subjectList.toArray(new Subject[subjectList.size()]);
    }

    //Note: writes new subject list by invoking writeSubjects method from Utils class    
    private void writeNewSubjects() {
        if(newSubjectAdded()) {
            Utils.writeSubjects(INPUT_FILE_NAME, toSubjectArray());
        }
    }

    //Note: newSubjectAdded is true when the size of subjectList is greater than initialSubjectSize.
    private boolean newSubjectAdded() {
        return initialSubjectSize < subjectList.size();
    }

    //Note: Main method
    public static void main(String[] args) {
        new TestSubject().run();
    }
}
