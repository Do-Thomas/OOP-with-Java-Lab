/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;
import java.util.ArrayList;
import java.util.Scanner;
import tools.Mytools;


public class Menu extends ArrayList<String>{
    public Menu(){
        super();
    }
    public Menu(String[] items){
        super();
        for (String item: items) this.add(item);
    }
    public  int getChoice (Object[] options){
        for (int i = 0; i < options.length; i ++){
            System.out.print((i+1) + "-" + options[i]);
        }
        System.out.print("Choose 1.." + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}
