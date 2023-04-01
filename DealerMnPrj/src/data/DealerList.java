package data;

import java.util.List;
import java.util.ArrayList;
import tools.Mytools;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        super();
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = Mytools.readLinesFromFile(dataFile);
        for (String line : lines) {
            String[] list = line.split("" + Dealer.SEPARATOR);
            if (list.length == 5) {
                this.add(new Dealer(list[0], list[1], list[2], list[3], Mytools.parseBool(list[4])));
            }
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList list = new DealerList(loginObj);
        for (Dealer dealer : this) {
            if (dealer.isContinuing()) {
                list.add(dealer);
            }
        }
        return list;
    }

    public DealerList getUnContinuingList() {
        DealerList list = new DealerList(loginObj);
        for (Dealer dealer : this) {
            if (!dealer.isContinuing()) {
                list.add(dealer);
            }
        }
        return list;
    }

//    private Dealer searchDealer(String sID) {
//        for (Dealer d : this) {
//            if (d.getID().equalsIgnoreCase(sID)) {
//                System.out.println(d);
//            }
//        }
//        return null;
//    }
//
//    public void searchDealer() {
//        Dealer d = searchDealer(Mytools.readPattern("Input ID:", "^[D|d]\\d{3}$"));
//        if (d == null) {
//            System.out.println("Result: ");
//        }
////        return -1;
//    }
    private int searchDealer(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public Dealer searchDealerID(String ID) {
        for (Dealer d : this) {
            if (d.getID().equalsIgnoreCase(ID)) {
                return d;
            }
        }
        return null;
    }

    public void searchDealerbyID() {
        Dealer d = searchDealerID(Mytools.readPattern("Input ID:", "^[D|d]\\d{3}$"));
        if (d != null) {
            System.out.println("Result: " + d);
        } else {
            System.out.println("NOT FOUND!");
        }
    }

    public void searchDealer() {
        String inputID;
        inputID = Mytools.readPattern("Enter ID: ", Dealer.ID_FORMAT);
        int pos;
        pos = searchDealer(inputID); //INDEX
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            System.out.print("Dealer: " + this.get(pos));
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        Dealer d;
        int pos;
        do {
            ID = Mytools.readPattern("ID of new dealer: ", "^[D|d]\\d{3}$").toUpperCase();
//            d = searchDealer(ID);
            pos = searchDealer(ID);
//            if (d != null) {
//                System.out.println("ID is duplicated!");
//            }
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = Mytools.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = Mytools.readNonBlank("Address of new dealer: ");
        phone = Mytools.readPattern("Phone number: ", PHONEPATTERN);
        continuing = true;
        Dealer d1 = new Dealer(ID, name, addr, phone, continuing);
        this.add(d1);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        String ID = Mytools.readPattern("ID of dealer: ", "^[D|d]\\d{3}$");
//        Dealer d = searchDealer(ID);
        int pos = searchDealer(ID);
        Dealer d = this.get(pos);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        }
        remove(pos);
        d.setContinuing(false);
        changed = true;
        System.out.println("Dealer has been un-continuing!");
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = Mytools.sc.nextLine();
        int pos;
//        Dealer d = searchDealer(ID);
        pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.println("new name, ENTER for omitting: ");
            newName = Mytools.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }

            String newAddr = "";
            System.out.print("new address, ENTER for omitting: ");
            newAddr = Mytools.sc.nextLine();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }

            String newPhone = "";
            System.out.print("new phone, ENTER for omitting: ");
            newPhone = Mytools.readPattern("new phone, ENTER for omitting: ", Dealer.PHONE_FORMAT);
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List");
        } else {
            System.out.println(this);
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUnContinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFile() {
        if (changed) {
            Mytools.writeFile(dataFile, this);
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
