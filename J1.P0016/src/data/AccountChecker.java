///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package data;
//
//import java.util.List;
//import tools.MyTool;
//
//
///**
// *
// * @author Asus
// */
//public class AccountChecker {
//
//    private String accFile;
//    private static String SEPERATOR = ",";
//    private String crrPassword;
//    private String crrAccName;
//    private String crrRole;
//    private List<String> lines;
//
//    public AccountChecker() {
//        setupAccFile();
//    }
//
//    private void setupAccFile() {
//        Config cR = new Config();
//        accFile  = cR.getAccountFile();
//    }    
//
//    public boolean check(Account acc) {
//        List<String> lines = MyTool.readLinesFromFile(accFile);
//        for (String line : lines) {
//            String[] parts = line.split(this.SEPERATOR);
//            if (parts.length < 3) {
//                return false;
//            }
//            if (parts[0].equalsIgnoreCase(acc.getAccName())
//                    && parts[1].equals(acc.getPwd())
//                    && parts[2].equalsIgnoreCase(acc.getRole())) {
//                return true;
//            }
//        }
//        return false;
//        read data in file
//        lines = MyTool.readLinesFromFile(accFile);
//        for(String line : lines){
//            String[] parts = line.split(SEPERATOR);
//            if(parts.length<3) return false;
//            if(parts[0].equalsIgnoreCase(acc.getAccName())&&
//               parts[1].equals(acc.getPwd())&&
//               parts[2].equalsIgnoreCase(acc.getRole())){
//                crrAccName = parts[0];
//                crrPassword = parts[1];
//                crrRole = parts[2];
//                return true;
//            }
//                
//        }
//        return false;
//    }
//    
//    public static void main(String[]args){
//        AccountChecker aChk = new AccountChecker();
//        Account acc = new Account ("E001", "12345678", "BOSS");
//        boolean valid = aChk.check(acc);
//        System.out.println("Needs OK, OK?: " + valid);
//        acc = new Account("E002", "23456789", "ACC_1");
//        valid = aChk.check(acc);
//        System.out.println("Needs OK, OK?: " + valid);
//        acc = new Account("E003", "123456789", "ACC_2");
//        valid = aChk.check(acc);
//        System.out.println("Needs NO OK, OK?: " + valid);
//    }
//}
package data;

import java.util.List;
import tools.MyTool;


public class AccountChecker {
    private String accFile;
    private static String SEPARATOR=",";
    private String crrPassword;
    private String crrAccName;
    private String crrRole;
    private List<String> lines;
    
    public AccountChecker(){
        setupAccFile();
    }
    private void setupAccFile(){
        Config cR = new Config();
        accFile = cR.getAccountFile();
    }
    public boolean check(Account acc){
        //read data in file
        lines = MyTool.readLinesFromFile(accFile);
        for(String line : lines){
            String[] parts = line.split(SEPARATOR);
            if(parts.length<3) return false;
            if(parts[0].equalsIgnoreCase(acc.getAccName())&&
               parts[1].equals(acc.getPwd())&&
               parts[2].equalsIgnoreCase(acc.getRole())){
                crrAccName = parts[0];
                crrPassword = parts[1];
                crrRole = parts[2];
                return true;
            }
                
        }
        return false;
    }
   
    public void changePassWord(){
        String newPwd;
        do{
            if(crrPassword.equals((MyTool.readNonBlank("Enter current password: ")))){
                boolean isValidPwd;
                do{
                    newPwd = MyTool.readNonBlank("Enter new password:");
                    if(!(isValidPwd = MyTool.validPassword(newPwd, 8))){
                        System.out.println("Password require at least 8 charater include text, number, special charater!");
                    }
                }while(!isValidPwd);
                for(int i = 0;i<lines.size();i++){
                    String line = lines.get(i);
                    String[] parts = line.split(SEPARATOR);
                    if(parts[0].equals(crrAccName)&&parts[1].equals(crrPassword)&&parts[2].equals(crrRole)){
                        line = parts[0]+SEPARATOR+newPwd+SEPARATOR+parts[2];
                        lines.set(i, line);
                        MyTool.writeFile(accFile, lines);
                        System.out.println("Password changed!");
                        return;
                    }
                }
            }else{
                System.out.println("Password is incorrect!");
            }
        }while(MyTool.readBool("Try again?"));
    }
    public static void main(String[] args){
        AccountChecker aChk = new AccountChecker();
        Account acc = new Account("E001", "12345678", "BOSS");
        boolean valid = aChk.check(acc);
        System.out.println("Needs ok, ok? "+valid);
        acc = new Account("E002", "23456789", "ACC-1");
        valid = aChk.check(acc);
        System.out.println("Needs ok, ok? "+valid);
        acc = new Account("E003", "123456789", "ACC-2");
        valid = aChk.check(acc);
        System.out.println("Needs no ok, ok? "+valid);
    }
}