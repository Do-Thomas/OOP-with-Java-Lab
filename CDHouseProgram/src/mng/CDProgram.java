
package mng;

import Tool.Mytool;
import data.CDList;
import java.text.ParseException;

public class CDProgram {
    public static void main(String[] args) throws ParseException {
        String[] options = {"Add CD to the catalog\n", "Search CD by CD title\n", "Display the catalog\n",
            "Update CD\n \t4.1 Update CD\n \t4.2 Delete CD\n \t4.3 Exit",
            "Save CD to file\n", "Print list of CDs"};
        Menu mnu = new Menu(options);
        CDList cList = new CDList();
        cList.initWithFile();
        int choice = 0;
        do {
            choice = mnu.getChoice("\nCD Program");
            switch (choice) {
                case 1:
                    cList.addCD();
                    break;
                case 2:
                    cList.searchByTitle();
                    break;
                case 3:
                    cList.printCatalog();
                    break;
                case 4:
                    int choice2 = 0;
                    String[] option2 = {"Update CD\n", "Delete CD\n", "Exit\n"};
                    Menu mnuUp = new Menu(option2);
                    do {
                        choice2 = mnuUp.getChoice("Update Product:");
                        switch (choice2) {
                            case 1:
                                cList.updateCD();
                                break;
                            case 2:
                                cList.deleteCD();
                                break;
                            case 3:
                               System.out.println("Exit");
                               boolean res = Mytool.readBool("Save to file?");
                                if (res) {
                                    cList.writeCDToFile();
                                }     
                                break;                                               
                        }
                    } while (choice2 > 0 && choice2 < 3);
                    break;
                case 5:
                     cList.writeCDToFile();
                    break;
                case 6:
                    cList.printAllCD();
                    break;
                default:
                        boolean res = Mytool.readBool("Data changed. Write to file? ");
                        if (res == true) {
                            cList.writeCDToFile();
                        }
            }
        } while (choice > 0 && choice <= mnu.size());
        System.out.println("Good Bye!");
    }
}
