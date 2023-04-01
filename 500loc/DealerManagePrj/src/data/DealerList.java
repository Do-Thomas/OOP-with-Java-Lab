package data;

import data.UsersList.Status;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import main.DealerManage;
import tools.MyTools;

/**
 *
 * @author Asus
 */
public class DealerList extends ArrayList<Dealer> {

    private static final String REMOVE = "disable";
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    public static final char SEPARATOR = '|';
    public static final String ID_FORMAT = "D\\D{3}";
    private String dealerFile = "";
    DealerManage listLogin = null;
    private boolean changed = false;
    String dataFromFile;

    public DealerList() {
        super();
        this.listLogin = listLogin;
    }

    public void loadDealerFromFile(String filename) throws IOException {
//        try {
//            File f = new File(filename);
//            if (!f.exists()) {
//                System.out.println("File not exist!");
//            }
//            FileReader fr = new FileReader(filename);//read()
//            BufferedReader br = new BufferedReader(fr);//readLine()
//            String s;
//            while ((s = br.readLine()) != null) {
//                StringTokenizer st = new StringTokenizer(s, "|");
//                while (st.hasMoreTokens()) {
//                    String id = st.nextToken().trim();
//                    String name = st.nextToken().trim();
//                    String address = st.nextToken().trim();
//                    String phone = st.nextToken().trim();
//                    String status = st.nextToken().trim();
//                    this.add(new Dealer(id, name, address, phone, status));
//                }
//            }
//            br.close();
//            fr.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        List<String> listDL = MyTools.readLinesFromFile(dealerFile);
        if (listDL == null) {
            return;
        } else {
            for (String x : listDL) {
                Dealer d = new Dealer(x);
                this.add(d);
            }
        }
    }

    public void initWithFile() throws IOException {
        Config cR = new Config();
        dealerFile = cR.getDealerFile();
        loadDealerFromFile(dealerFile);
    }

    public int searchID(String ID) {
        ID = ID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(ID)) {
                return i;
            }
        }
        System.out.print("Not found\n");
        return -1;
    }

    public Dealer searchDealerID(String ID) {
        ID = ID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(ID)) {
                return this.get(i);
            }
        }
        System.out.print("Not found\n");
        return null;
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        Status status;
        boolean continuing;
        int pos;
        Dealer d;
        do {
            ID = MyTools.readPattern("ID of new dealer: ", "^[D|d]\\d{3}$").toUpperCase();
            d = searchDealerID(ID);
            if (d != null) {
                System.out.println("ID is duplicated!");
            }
        } while (d != null);
        name = MyTools.readNonBlank("Name of new dealer").toUpperCase();
        addr = MyTools.readNonBlank("Address of new dealer");
        phone = MyTools.readPattern("Phone number", PHONEPATTERN);
//        status = MyTools.readStatus("New status of dealer", "enable", "disable");
        
        continuing = true;
        Dealer d1 = new Dealer(ID, name, addr, phone, addr);
        this.add(d1);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public boolean removeDealer() {
        System.out.println("Enter Dealer ID to remove: ");
        String removedID = MyTools.sc.nextLine();
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            remove(pos);
//            this.get(pos).getStatus().replace("enable", "disable");
            for (Dealer thi : this) {
                System.out.println(thi);
            }
            System.out.println("Moved successfully");
        }
        return true;
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTools.sc.nextLine();
        Dealer d = searchDealerID(ID);
        if (d == null) {
            System.out.println("Dealer " + ID + "not found!");
        } else {
            String newName = "";
            System.out.println("new name, ENTER for omitting: ");
            newName = MyTools.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            System.out.println("Dealer list: ");
            for (Dealer dealer : this) {
                System.out.println(dealer);
            }
        }
    }

    public DealerList getContinuingList() {
        DealerList continuingList = new DealerList();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).isIscontinuing()) {
                continuingList.add(this.get(i));
            }
        }
        return continuingList;
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public DealerList getUNContinuingList() {
        DealerList UNcontinuingList = new DealerList();
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).isIscontinuing()) {
                UNcontinuingList.add(this.get(i));
            }
        }
        return UNcontinuingList;
    }

    public void printUNcontinuingDealers() {
        this.getUNContinuingList().printAllDealers();
    }

    public void writeDealerToFile(String fileName) {
        if (changed) {
            MyTools.writeFile(dataFromFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
