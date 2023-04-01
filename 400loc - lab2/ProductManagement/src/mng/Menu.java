
package mng;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu extends ArrayList<String>{
    public Menu() {
        super();
    }

    public Menu(String[] items) {
        super();
        for (String item : items) {
            this.add(item);
        }
    }

    public int getChoice(String title) {
        System.out.println(title);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
        System.out.println("Press other to quit.");
        System.out.print("Choose [1.." + this.size() + "]: ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
