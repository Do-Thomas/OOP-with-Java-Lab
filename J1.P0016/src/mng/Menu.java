/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import java.util.ArrayList;
import java.util.Scanner;
import tools.MyTool;

/**
 *
 * @author Asus
 */
public class Menu extends ArrayList<String>{
     static Scanner sc = new Scanner(System.in);
     
    public Menu() {
        super();
    }

//    public Menu(String[] items) {
//        super();
//        for (String item : items) {
//            this().add(item);
//        }
//    }

//    public int getChoice(String title) {
//        System.out.println(title);
//        int choice;
//        for (int i = 0; i < title.length(); i++) {
//            System.out.println("   " + (i + 1) + "-"+ this.get(i));
//        }
//  
//        System.out.println("   Other for quit.");
//        System.out.print("Choose [1..8]: ");
//        choice = MyTool.SC.nextInt();
//        return choice;
//    }
    
   

    // lấy lựa chọn từ người dùng
    public int getChoice() {
        int i = 1;
        for(String option : this) {
            System.out.println((i++)+"-"+option);
        }
        System.out.println("Choose: ");
        return Integer.parseInt(sc.nextLine());
    }
}
