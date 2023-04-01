package data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tools.Mytool;

public class ProductList extends ArrayList<Product> {

    Product productObj = null;
    private String dataFile = "";
    boolean changed = false;

    public ProductList(Product productObj) {
        this.productObj = productObj;
    }

    public ProductList() {
    }

    private void loadProductFromFile() throws ParseException {
        List<String> lines = Mytool.readLinesFromFile(dataFile);
        for (String line : lines) {
            this.add(new Product(line));
        }
    }

    public void initWithFile() throws ParseException {
        Config cR = new Config();
        dataFile = cR.getPRODUCT_FILE();
        loadProductFromFile();
    }

    public void printAllProducts() {
        if (this.isEmpty()) {
            System.out.println("Empty list!");
        } else {
            for (Product p : this) {
                p.print();
            }
        }
    }

    public void printAllProductInFile() throws ParseException {
        List<String> lines = Mytool.readLinesFromFile(dataFile);
        ArrayList<Product> list = new ArrayList<>();
        for (String line : lines) {
            list.add(new Product(line));
        }
        Collections.sort(list);
        for (Product product : list) {
            product.print();
        }
    }

    public void checkExistProduct() {
        boolean check = false;
        boolean path;
        path = Mytool.readPathFile(dataFile);
        String find = Mytool.readPattern("Enter product name", Product.PRODUCT_PATTERN);
        for (Product p : this) {
            if (p.getName().toLowerCase().contains(find.toLowerCase())) {
                check = true;
                p.print();

            }
        }
        if (check == true) {
            System.out.println("Product found!");
        } else {
            System.out.println("Not found!");
        }
    }

    public void searchProduct() {
        String find = Mytool.readPattern("Enter product name", Product.PRODUCT_PATTERN);
        boolean cont = true;
        for (Product p : this) {
            if (p.getName().toLowerCase().contains(find.toLowerCase())) {
                p.print();
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
            productID = Mytool.readPattern("New product ID", Product.ID_FORMAT);
            productID = productID.toUpperCase();
            pos = checkID(productID);
            if (pos >= 0) {
                System.out.println("Product ID is duplicated!");
            }
        } while (pos >= 0);
        do {
            name = Mytool.readPattern("Name of new product", Product.PRODUCT_PATTERN);
            if (searchProduct(name)) {
                System.out.println("Product name is duplicated!");
            }
        } while (searchProduct(name));
        price = Mytool.readRangeDouble("Price of new product", 0, 10000);
        quantity = Mytool.readRangeInt("Quantity of new product(0 to 100)", 0, 100);
        status = Mytool.readStatus("Status of new product(Available or Not Available)");
        Product p = new Product(productID, name, price, quantity, status);
        this.add(p);
        System.out.println("New product has been added.");
        changed = true;
    }

    public void writeProductToFile() throws ParseException {
        if (changed) {
            Mytool.writeFile(dataFile, this);
            changed = false;
            System.out.println("Product save successfully");
            loadProductFromFile();
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    //Change vailable to not vailable. Not delete data.
    public void deleteProduct() {
        String productID = Mytool.readPattern("Enter ID needs removing", Product.ID_FORMAT);
        int pos = checkID(productID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            Product deP = this.get(pos);
            String deleteProduct = "Not Available";
            deP.setStatus(deleteProduct);
            System.out.println("Product has been deleted.");
        }
        changed = true;
    }

    public void updateProduct() {
        String productID = Mytool.readPattern("Enter ID needs updating", Product.ID_FORMAT);
        int pos = checkID(productID);
        String name;
        double price;
        int quantity;
        String status;
        boolean isQuantity;
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            do {
                name = Mytool.readPattern("Name of new product", Product.PRODUCT_PATTERN);
                if (searchProduct(name)) {
                    System.out.println("Product name is duplicated!");
                }
            } while (searchProduct(name));
            price = Mytool.readRangeDouble("Price of new product", 0, 10000);
            quantity = Mytool.readRangeInt("Quantity of new product(0 to 100)", 0, 100);
            status = Mytool.readStatus("Status of new product");
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
