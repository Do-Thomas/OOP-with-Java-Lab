/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class MyTool {
    public static final Scanner SC = new Scanner(System.in);
    
    public static boolean validStr(String str, String regEx){
        if(str.matches(regEx)){
            return true;
        } else return false;
    }
    
    public static boolean validPassword(String str, int minLen){
        if(str.length() < minLen){
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*") 
                && str.matches(".*[\\d]+.*") 
                && str.matches(".*[\\W]+.*");
    }
    
    public static Date parseDate(String dateStr, String dateFormat){
        SimpleDateFormat dF = (SimpleDateFormat)SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try{
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public static String dataToStr(Date date, String dateFormat){
        SimpleDateFormat df = (SimpleDateFormat)SimpleDateFormat.getInstance();
        df.applyPattern(dateFormat);
        String d = df.format(date);
        return d;
    }
    
    public static boolean parseBool(String boolStr){
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1'|| c == 'Y' || c == 'T');
    }
    
    public static String readNonBlank(String message){
        String input = "";
        do{
            System.out.print(message);
            input = SC.nextLine().trim();
        }
        while (input.isEmpty());
        return input;
    }
    
    public static String readPattern(String message, String pattern){
        String input = "";
        boolean valid;
        do{
            System.out.print(message);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while(!valid);
        return input;
    }
    
    public static boolean readBool(String message){
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if(input.isEmpty()) return false;
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }
     
    public static List<String> readLinesFromFile(String filename){
        List<String> list = new ArrayList<String>();
        try{
            File f = new File(filename);
            if(!f.exists()){
                return list;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine()) != null){
                details.trim();
                if(!details.isEmpty()){
                    list.add(details);
                }
            }
            bf.close();
            fr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    
    public static void writeFile(String filename, List list){
        if(list.size() == 0){
            System.out.println("Empty list.");
            return;
        }
        try{
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(int i = 0; i < list.size(); i++){
                pw.println(list.get(i));
            }
            pw.close();
            fw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
