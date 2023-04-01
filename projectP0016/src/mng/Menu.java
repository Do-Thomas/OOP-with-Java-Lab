
package mng;

import java.util.Arrays;
import tools.MyTool;


public class Menu extends java.util.ArrayList<String> {
    public Menu(){
        super();
    }
    public Menu(String[] items){
        super();
        this.addAll(Arrays.asList(items));
    }
    
    public int getChoice(String title){
        System.out.println(title);
        for(int i = 0;i<this.size();i++){
            System.out.println(" "+(i+1)+".  "+this.get(i));
        }
        return Integer.parseInt(MyTool.readPattern("Your choice: ", "\\d{1,2}"));
    }
    
    
}
