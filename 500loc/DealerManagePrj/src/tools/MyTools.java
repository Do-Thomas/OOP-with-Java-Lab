package tools;

import data.UsersList.Status;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class MyTools {

    public static final Scanner sc = new Scanner(System.in);


    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d}+.*")
                && str.matches(".*[\\W]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        boolean cont = false;
        String result = "";
        return result;
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + " ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + " ");
            input = sc.nextLine().trim();
            valid = input.matches(pattern);
        } while (!valid);
        return input;
    }
    
    public static String readStatus(String message, String pattern1, String pattern2) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + " ");
            input = sc.nextLine().trim();
            valid = input.matches(pattern1) || input.matches(pattern2);
        } while (!valid);
        return input;
    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<String>();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return list;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                details.trim();
                if (!details.isEmpty()) {
                    list.add(details);
                }
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        File f = new File(filename);
        String Path = f.getAbsolutePath();
        try {
            try (FileWriter fw = new FileWriter(f)) {
                PrintWriter pw = new PrintWriter(fw);
                for (short i = 0; i < list.size(); i++) {
                    fw.write(list.get(i) + "\n");
                }
                fw.close();
                pw.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error Save File!");

        }
    }
}
