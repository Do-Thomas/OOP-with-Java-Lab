
package data;

import java.text.ParseException;
import tools.MyTool;

public class Product implements Comparable<Product> {
    public static final String PRODUCT_PATTERN = "[a-zA-Z0-9\" \"]{5,100}";
    public static final String ID_FORMAT = "P\\d{3}";
    String productID;
    String name;
    double price;
    int quantity;
    String status;
    public static final char SEPARATOR = ',';

    public Product() {
        productID = "";
        name = "";
        price = 0.0;
        quantity = 0;
        status = "";
    }

    public Product(String productID, String name, double price, int quantity, String status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    Product(String line) throws ParseException {
        String[] parts = line.split("" + Product.SEPARATOR);
        productID = parts[0].trim();
        name = parts[1].trim();
        price = MyTool.parseDouble(parts[2]); //parse double
        quantity = MyTool.parseInt(parts[3]); //parse int
        status = parts[4].trim();
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return productID + SEPARATOR + name + SEPARATOR + price + SEPARATOR + quantity + SEPARATOR + status;
    }

    @Override
    public int compareTo(Product product) {
        if (this.getQuantity() == product.getQuantity()) {
            if (this.getPrice() > product.getPrice()) {
                return -1;
            } else if (this.getPrice() == product.getPrice()) {
                return 0;
            } else {
                return 1;
            }
        } else if (this.getQuantity() > product.getQuantity()) {
            return -1;
        } else {
            return 1;
        }
    }

}
