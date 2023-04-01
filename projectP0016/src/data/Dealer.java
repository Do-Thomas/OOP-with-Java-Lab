
package data;

public class Dealer implements Comparable<Dealer>{
    public static final String SEPARATOR = ",";
    public static final String ID_FORMAT = "D\\d{3}";
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";
    private String ID;
    private String name;
    private String phone;
    private String addr;
    private boolean continuing;

    public Dealer(String ID, String name, String addr, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.addr = addr;
        this.continuing = continuing;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }
    

    @Override
    public int compareTo(Dealer o) {
        return this.ID.compareTo(o.getID());
    }
    public String toString(){
        return ID+SEPARATOR+name+SEPARATOR+addr+SEPARATOR+phone+SEPARATOR+continuing;
    }
    
    
}
