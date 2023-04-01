
package mng;

import data.ProductList;
import java.text.ParseException;
import tools.MyTool;


public class ProductMng {
    public static void main(String[] args) throws ParseException {
        String[] options = {"Print all product\n", "Create new product\n", "Check exist product\n", 
                            "Search product's information by name\n", "Update product\n",
                            "Delete product\n", "Save product to file\n", "Print list of product from file"};
        Menu mnu = new Menu(options);
        ProductList pList = new ProductList();
        pList.initWithFile();
        int choice = 0;
        do {
            choice = mnu.getChoice("Product Manager");
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
                    pList.updateProduct();
                    break;
                case 6:
                    pList.deleteProduct();
                    break;
                case 7:
                    pList.writeProductToFile();
                    break;
                case 8:
                    pList.printAllProductInFile();
                    break;
                default:
                    if (pList.isChanged()) {
                        boolean res = MyTool.readBool("Data changed. Write to file? ");
                        if (res == true) {
                            pList.writeProductToFile();
                        }
                    }
            }
        } while (choice > 0 && choice <= mnu.size());
        System.out.println("Good Bye!");
    }

}
