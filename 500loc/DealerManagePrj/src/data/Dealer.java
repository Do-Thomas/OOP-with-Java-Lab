
package data;

import data.UsersList.Status;

/**
 *
 * @author Asus
 */
public class Dealer {

    private String ID;
    private String name;
    private String address;
    private String phone; 
    private String status;
    private boolean iscontinuing;

    public Dealer() {
    }
    
    public Dealer(String line) {
        String[] parts = line.split("\\|");
        this.ID = parts[0].trim();
        this.name = parts[1].trim();
        this.address = parts[2].trim();
        this.phone = parts[3].trim();
        this.status = parts[4].trim();
    }

//    public Dealer(String ID) {
//        this.ID = ID;
//    }

    //String status
    public Dealer(String ID, String name, String address, String phone, String status) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public boolean isIscontinuing() {
        return iscontinuing;
    }

    public void setIscontinuing(boolean iscontinuing) {
        this.iscontinuing = iscontinuing;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
       return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return ID + ", " + name + ", " + address + 
                ", " + phone + ", " + iscontinuing + "\n";
    }
}
