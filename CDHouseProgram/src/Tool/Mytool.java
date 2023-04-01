package Tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mytool {
    
    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static double parseDouble(String doubleStr) throws ParseException {
        String s = doubleStr.trim();
        return Double.parseDouble(s);
    }

    public static int parseInt(String intStr) throws ParseException {
        String s = intStr.trim();
        return Integer.parseInt(s);
    }
    
    public static int readPublicYear(String message) {
        int year;
        do {            
            System.out.print(message + "");
            Scanner sc = new Scanner(System.in);
            year = Integer.parseInt(sc.nextLine());
        } while (year <= 1980);
        return year;
    }

    public static int readRangeInt(String message, int min, int max) {
        int input = 0;
        do {
            System.out.print(message + "");
            Scanner SC = new Scanner(System.in);
            input = SC.nextInt();
        } while (input <= min && input >= max);
        return input;
    }

    public static double readRangeDouble(String message, double min, double max) {
        double input = 0;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextDouble();
        } while (input <= min && input >= max);
        return input;
    }

    public static String readNonBlank(String message) { 
        String input = "";
        do {
            System.out.print(message + "");
            Scanner SC = new Scanner(System.in);
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    } 

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message + "");
            Scanner SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    } 

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        Scanner SC = new Scanner(System.in);
        input = SC.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static boolean readPathFile(String filename) {
        File f = new File(filename);
        String absolutePath = f.getAbsolutePath();
        if (!f.exists()) {
            System.out.println("The File: " + filename + " does not exist!");
            return false;
        } else {
            System.out.println(absolutePath);
            System.out.println("Exist Product");
        }
        return true;
    }

    public static List<String> readLinesFromFile(String filename) {
        ArrayList<String> list = new ArrayList();
        File f = new File(filename);
        String absolutePath;
        absolutePath = f.getAbsolutePath();
        System.out.println(absolutePath);
        if (f.exists()) {
            String line;
            try {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                while ((line = bf.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        list.add(line);
                    }
                }
                bf.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("The file: " + filename + "does not exist!");
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        if (list != null && !list.isEmpty()) {
            try {
                FileWriter fw = new FileWriter(filename);
                PrintWriter pw = new PrintWriter(fw);
                for (Object item : list) {
                    pw.println(item);
                }
                fw.close();
                pw.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static String readStatus(String message) {
        String input;
        boolean valid;
        do {
            System.out.print(message + ": ");
            Scanner SC = new Scanner(System.in);
            input = SC.nextLine().trim();
            valid = (input.equalsIgnoreCase("available") || input.equalsIgnoreCase("not available"));
        } while (!valid);
        return input;
    }
}
