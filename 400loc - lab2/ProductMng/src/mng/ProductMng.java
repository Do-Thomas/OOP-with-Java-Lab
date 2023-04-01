package mng;

import data.ProductList;
import java.text.ParseException;
import tools.Mytool;

public class ProductMng {

    public static void main(String[] args) throws ParseException {
        String[] options = {"Print all product\n", "Create new product\n", "Check exist product\n",
            "Search product's information by name\n", "Update product\n \t5.1 Update Product\n \t5.2 Delete Product\n \t5.3 Exit",
            "Save product to file\n", "Print list of product from file"};
        Menu mnu = new Menu(options);
        ProductList pList = new ProductList();
        pList.initWithFile();
        int choice = 0;
        do {
            choice = mnu.getChoice("\nProduct Manager");
            switch (choice) {
                case 1:
                    pList.printAllProducts();
                    break;
                case 2:
                    pList.addProduct();
                    break;
                case 3:
                    pList.checkExistProduct();
                    break;
                case 4:
                    pList.searchProduct();
                    break;
                case 5:
                    int choice2 = 0;
                    String[] option2 = {"Update Product\n", "Delete Product\n", "Exit\n"};
                    Menu mnuUp = new Menu(option2);
                    do {
                        choice2 = mnuUp.getChoice("Update Product:");
                        switch (choice2) {
                            case 1:
                                pList.updateProduct();
                                break;
                            case 2:
                                pList.deleteProduct();
                                break;
                            case 3:
                               System.out.println("Exit");
                               boolean res = Mytool.readBool("Save to file?");
                                if (res) {
                                    pList.writeProductToFile();
                                }     
                                break;                                               
                        }
                    } while (choice2 > 0 && choice2 < 3);
                    break;
                case 6:
                    pList.writeProductToFile();
                    break;
                case 7:
                    pList.printAllProductInFile();
                    break;
                default:
                    if (pList.isChanged()) {
                        boolean res = Mytool.readBool("Data changed. Write to file? ");
                        if (res == true) {
                            pList.writeProductToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= mnu.size());
        System.out.println("Good Bye!");
    }

}
