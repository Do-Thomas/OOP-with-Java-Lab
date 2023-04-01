package tools;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-z]+.*")
                &&// at leat 1 character
                str.matches(".*[\\d]+.*")
                && //at least 1 digit
                str.matches(".*[\\W]+.*");// at least 1 special char
    }

    public static Date pasreDate(String dateStr, String dateFormat) {
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
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        return dF.format(date);
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String msg) {
        String input = "";
        do {
            System.out.print(msg);
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String msg, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(msg);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String msg) {
        String input = "";
        System.out.println(msg + "[1/0-Y/N-T/F}:");
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File does not exist!");
            return null;
        }
        ArrayList<String> data = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return data;
    }

    public static void writeFile(String filename, List list) {
        File f = new File(filename);
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Tests with phone numbers:");
        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
        System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));

        //test pwd
        System.out.println("Test with password:");
        System.out.println(validPassword("qwerty", 8));
        System.out.println(validPassword("qwertyABC", 8));
        System.out.println(validPassword("12345678", 8));
        System.out.println(validPassword("qbc123456", 8));
        System.out.println(validPassword("qbc@123456", 8));
        //id format D000
        System.out.println("Test with IDs:");
        System.out.println(validStr("A0001", "D\\d{3}"));
        System.out.println(validStr("10001", "D\\d{3}"));
        System.out.println(validStr("D0001", "D\\d{3}"));
        System.out.println(validStr("D101", "D\\d{3}"));
        //Test date format
        Date d = pasreDate("2022:12:07", "yyyy:MM:dd");
        System.out.println(d);
        System.out.println(dataToStr(d, "dd/MM/yyyy"));
        d = pasreDate("12/07/2022", "MM/dd/yyyy");
        System.out.println(d);
        d = pasreDate("2000/29/02", "yyyy/dd/MM");
        System.out.println(d);
        d = pasreDate("2000/30/02", "yyyy/dd/MM");
        System.out.println(d);
        d = pasreDate("2000/40/16", "yyyy/dd/MM");
        System.out.println(d);

        // test input data
        String input = readNonBlank("Input a non-blank string:");
        System.out.println(input);
        input = readPattern("Phone 9/11 digits", "\\d{9}|\\d{11}");
        System.out.println(input);
        input = readPattern("ID - format X00000", "X\\d{5}");
        System.out.println(input);
        boolean b = readBool("Input booleam");
        System.out.println(b);
    }

}
