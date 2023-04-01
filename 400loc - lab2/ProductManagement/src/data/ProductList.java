
package data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tools.MyTool;


public class ProductList extends ArrayList<Product>{
    Product productObj = null;
    private String dataFile = "";
    boolean changed = false;

    public ProductList(Product productObj) {
        this.productObj = productObj;
    }

    public ProductList() {
    }

    private void loadProductFromFile() throws ParseException {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for (String line : lines) {
            this.add(new Product(line));
        }
    }

    public void initWithFile() throws ParseException {
        Config cR = new Config();
        dataFile = cR.getProductFile();
        loadProductFromFile();
    }

    public void printAllProducts() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            for (Product p : this) {
                System.out.println(p);
            }
        }
    }

    public void printAllProductInFile() throws ParseException {
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        ArrayList<Product> list = new ArrayList<>();
        for (String line : lines) {
            list.add(new Product(line));
        }
        Collections.sort(list);
        for (Product product : list) {
            System.out.println(product);
        }
    }

    public void checkExistProduct() {
        boolean check = false;
        String find = MyTool.readPattern("Enter product name", Product.PRODUCT_PATTERN);
        for (Product p : this) {
            if (p.getName().toLowerCase().contains(find.toLowerCase())) {
                check = true;
            }
        }
        if (check == true) {
            System.out.println("Product found!");
        } else {
            System.out.println("Not found!");
        }
    }

    public void searchProduct() {
        String find = MyTool.readPattern("Enter product name", Product.PRODUCT_PATTERN);
        boolean cont = true;
        for (Product p : this) {
            if (p.getName().toLowerCase().contains(find.toLowerCase())) {
                System.out.println(p);
                cont = false;
            }
        }
        if (cont) {
            System.out.println("Not found!");
        }
    }

    public boolean searchProduct(String name) {
        for (Product p : this) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public int checkID(String ID) {
        if (this.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    public void addProduct() {
        String productID;
        String name;
        double price;
        int quantity;
        String status;
        int pos;
        do {
            productID = MyTool.readPattern("New product ID", Product.ID_FORMAT);
            productID = productID.toUpperCase();
            pos = checkID(productID);
            if (pos >= 0) {
                System.out.println("Product ID is duplicated!");
            }
        } while (pos >= 0);
        do {
            name = MyTool.readPattern("Name of new product", Product.PRODUCT_PATTERN);
            if (searchProduct(name)) {
                System.out.println("Product name is duplicated!");
            }
        } while (searchProduct(name));
        price = MyTool.readRangeDouble("Price of new product", 0, 10000);
        quantity = MyTool.readRangeInt("Quantity of new product", 0, 1000);
        status = MyTool.readStatus("Status of new product").toUpperCase();
        Product p = new Product(productID, name, price, quantity, status);
        this.add(p);
        System.out.println("New product has been added.");
        changed = true;
    }

    public void writeProductToFile() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
            System.out.println("Success");
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void deleteProduct() {
        String productID = MyTool.readPattern("Enter ID needs removing", Product.ID_FORMAT);
        int pos = checkID(productID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            this.remove(pos);
            System.out.println("Product has been deleted.");
        }
        changed = true;
    }

    public void updateProduct() {
        String productID = MyTool.readPattern("Enter ID needs updating", Product.ID_FORMAT);
        int pos = checkID(productID);
        String name;
        double price;
        int quantity;
        String status;
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            do {
                name = MyTool.readPattern("Name of new product", Product.PRODUCT_PATTERN);
                if (searchProduct(name)) {
                    System.out.println("Product name is duplicated!");
                }
            } while (searchProduct(name));
            price = MyTool.readRangeDouble("Price of new product", 0, 10000);
            quantity = MyTool.readRangeInt("Quantity of new product", 0, 1000);
            status = MyTool.readStatus("Status of new product");
            this.get(pos).setProductID(productID);
            this.get(pos).setName(name);
            this.get(pos).setPrice(price);
            this.get(pos).setQuantity(quantity);
            this.get(pos).setStatus(status);
            System.out.println("Product has been updated");
        }
        changed = true;
    }
}
