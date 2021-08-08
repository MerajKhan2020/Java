package testsubject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;




public class Utils {

    //Note:  method below is invoked to join subject name and subject code in parseSubjectFromLine method    
    public static String strJoin(String[] aArr, String sSep) {
        StringBuilder sbStr = new StringBuilder();
        for (int i = 0, il = aArr.length; i < il; i++) {
            if (i > 0)
                sbStr.append(sSep);
            sbStr.append(aArr[i]);
        }
        return sbStr.toString();
    }

    //Note: This Method gets invoked to read subject name and subject code from Text file   
    public static List<Subject> readSubjects(String inputFileName) {
        List<Subject> subjectList = new ArrayList<>();

        try {
            Scanner input = new Scanner(new File(inputFileName));
            while (input.hasNextLine()) {
                String subjectLine = input.nextLine();
                if (subjectLine != null && !subjectLine.isEmpty()) {
                    Subject parsedSubject = parseSubjectFromLine(subjectLine);
                    
                    subjectList.add(parsedSubject);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file : " + inputFileName);
            System.exit(0);
        }

        return subjectList;
    }

    //Note: Method below is called to ensure that the Text file has valid subject name and Code entries. 
    public static Subject parseSubjectFromLine(String subjectLine) {
        String[] tokens = subjectLine.split(" ");

        if (tokens.length < 2) {
            throw new RuntimeException("Invalid Input! " + subjectLine);
        }

        String code = tokens[tokens.length - 1];
        String[] nameTokens = Arrays.copyOfRange(tokens, 0, tokens.length - 1);
        String name = Utils.strJoin(nameTokens, " ");
        return new Subject(name, code);
    }


    //Note: This method writes data back in the Text File by passing value to printSubjects method.     
    public static void writeSubjects(String fileName, Subject[] subjects) {
        StringBuilder allSubjects = new StringBuilder();

        for(int i = 0; i < subjects.length; i++) {
            allSubjects.append(subjects[i].toString()).append(System.lineSeparator());
            //System.lineSeparator takes into consideration the line separator of the current os. 
           
        }

        try {
            Files.write(Paths.get(fileName), allSubjects.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }
}
