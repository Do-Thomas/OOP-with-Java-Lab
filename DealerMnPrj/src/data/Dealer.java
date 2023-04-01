/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import tools.Mytools;

public class Dealer implements Comparable<Dealer> {
     public static final char SEPARATOR = ',';
     public static final String ID_FORMAT = "D\\D{3}";
     public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";
     private String ID;
     private String name;
     private String addr;
     private String phone;
     private boolean continuing;
    
    public Dealer(String ID, String name, String addr, String phone, 
            boolean counting) {
    
        this.ID = ID;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCounting() {
        return continuing;
    }

    public void setCounting(boolean counting) {
        this.continuing = counting;
    }

    @Override
    public String toString() {
        return ID + ", " + name + ", " + addr + 
                ", " + phone + ", " + continuing+ "\n";
    }
    
     @Override
    public int compareTo(Dealer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

   
}
