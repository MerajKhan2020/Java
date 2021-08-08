package testsubject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Subject {
    private String name; //Subject Name
    private String code; //Subject Code

    
   //Note:  Contructor to set data fields for Subject name and Subject code    
    public Subject(String name, String code) {
        this.name = name.toUpperCase();
        this.code = code.toUpperCase();
    }

    //Note: Get Method  
    public String getName() {
        return name;
    }

    //Note: Get Method 
    public String getCode() {
        return code;
    }

    //Note: Get Method   
    public String getDiscipline() {
        return this.code.substring(0, 3);
    }

    //Note: Returns boolean value indicating if the subject code matches the string argument provided  
    public boolean codeMatches(String sub) {
        return this.code.matches(sub);
    }

    //Note: toString to print a string representation of the object   
    public String toString() {
        return name+" "+code;
    }

    //Note:  allDisciplines method will accept an array of Subject objects. It will return an array 
    //containing the different 3-character discipline codes represented in the array of subjects
    //in alphabetically order.
    public static String[] allDisciplines(Subject[] subjects) {
        String[] allCodes = new String[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            allCodes[i] = subjects[i].getDiscipline();
        }

        HashSet<String> uniqueDisciplines = new HashSet<>(Arrays.asList(allCodes));

        String[] uniqueDisciplinesArray = uniqueDisciplines.toArray(new String[uniqueDisciplines.size()]);
        Arrays.sort(uniqueDisciplinesArray);


        return uniqueDisciplinesArray;
    }


    //Note: ValidCode method will accept a string that is a possible new subject code, and
    //return a boolean indicating whether it satisfies the structural requirements for a subject code.
    public static boolean ValidCode(String newSubjectCode) {
        if(newSubjectCode.length() != 6)
            return false;
        else
        {
            String stringPart = newSubjectCode.substring(0,3);
            String numberPart = newSubjectCode.substring(3,6);
            stringPart = stringPart.toUpperCase();

            for(int i = 0; i < stringPart.length(); i++)
            {
                if (stringPart.charAt(i) < 'A' || stringPart.charAt(i) > 'Z' )
                    return false;
            }
            for (int i = 0; i < numberPart.length(); i++)
            {
                if(numberPart.charAt(i) < '0' || numberPart.charAt(i) > '9')
                    return false;
            }
            return true;

        }
    }

 
    //Note: codeExists method will accept an array of Subject objects and a possible new subject code. 
    //It will return a boolean indicating whether that code has already been allocated to
    //one of the subjects in the array.
    public static boolean codeExists(Subject[] subjects, String newSubjectCode) {
        for (Subject subject : subjects) {
            if (subject.codeMatches(newSubjectCode)) {
                return true;
            }
        }
        return false;
    }

    
    //Note: sortDisciplines method will accept an array of Subjects objects. It will return the sorted array of subjects in alphabetically order.  
        public static Subject[] sortDisciplines(Subject[] subjects) {
        Subject[] newSubjects = new Subject[subjects.length];
        for (int i = 0; i < newSubjects.length; i++) {
            newSubjects[i] = subjects[i];
        }
        Arrays.sort(newSubjects);
        return newSubjects;
    }

    
    //Note:  codesPerDiscipline method will accept an array of Subject objects and a 3-character discipline code. 
    //It will return an array containing the different subject codes represented in the array of subjects for the particular discipline. 
    public static String[] codesPerDiscipline(Subject[] subjects, String disciplineCode) {
        HashSet<String> subjectCodes = new HashSet<>();

        for (Subject subject : subjects) {
            if (subject.getDiscipline().equals(disciplineCode)) {
                subjectCodes.add(subject.code);
            }
        }

        return subjectCodes.toArray(new String[subjectCodes.size()]);
    }


}
