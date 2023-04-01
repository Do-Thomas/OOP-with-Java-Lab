package data;

import Tool.Mytool;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CDList extends ArrayList<CD> {

    public static final String CD_PATTERN = "[a-zA-Z0-9\"]{5,100}";
    public static final String ID_FORMAT = "C\\d{3}";

    CD cdObj = null;
    private String dataFile = "";
    boolean changed = false;
    int checkName = 0;
    int checkCDT = 0; 
    boolean flak = false; 

    public CDList(CD cdObj) {
        this.cdObj = cdObj;
    }

    public CDList() {
    }
    
    private void loadProductFromFile() throws ParseException {
        List<String> lines = Mytool.readLinesFromFile(dataFile);
        for (String line : lines) {
            this.add(new CD(line));
        }
    }

    public void initWithFile() throws ParseException {
        Config CR = new Config();
        dataFile = CR.getCD_FILE();
        loadProductFromFile();
    }

    public void searchByTitle() {
        String titleCD;
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            titleCD = Mytool.readNonBlank("Enter title CD to search: ");
            flak = true;
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getTitleCD().contains(titleCD)) {
                    System.out.println("CD title = " + titleCD
                            + " - CD result is shown as: "
                            + this.get(i).toString()
                            + "\n--------------------");
                    flak = false;
                }
            }
        }
        if (flak) {
            System.out.println("not found or nothing.");
        }
    }

    public int checkID(String ID) {
        if (this.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getIdCD().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    public boolean searchCD(String name) {
        for (CD c : this) {
            if (c.getNameCD().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void addCD() {
        String nameCD;
        String typeCD;
        String titleCD;
        double priceCD;
        String idCD;
        int publicYear;
        int pos;
        do {  
            nameCD = Mytool.readNonBlank("Enter CD name(game/movie/music): ");
            switch (nameCD) {
                case "game":
                    checkName++;
                    break;
                case "movie":
                    checkName++;
                    break;
                case "music":
                    checkName++;
                    break;
            }
        } while (nameCD.isEmpty());

        do { 
            typeCD = Mytool.readNonBlank("Enter type CD(audio/video): ");
            switch (typeCD) {
                case "audio":
                    checkCDT++;
                    break;
                case "video":
                    checkCDT++;
                    break;
                default:
                    System.out.println("Please put in right format.");
                    break;
            }
        } while (typeCD.isEmpty());

        do {
            titleCD = Mytool.readPattern("Name of new CD: ", CD_PATTERN);
            if (searchCD(typeCD)) {
                System.out.println("CD name is duplicated!");
            }
        } while (searchCD(titleCD));
        priceCD = Mytool.readRangeDouble("Price of new CD", 0, 10000);
        do {
            idCD = Mytool.readPattern("New CD ID(Cxxx): ", ID_FORMAT);
            idCD = idCD.toUpperCase();
            pos = checkID(idCD);
            if (pos >= 0) {
                System.out.println("Product ID is duplicated!");
            }
        } while (pos >= 0);
        publicYear = Mytool.readPublicYear("Enter public year: ");
        CD p = new CD(nameCD, typeCD, titleCD, priceCD, idCD, publicYear);
        this.add(p);
        changed = true;
        System.out.println("New CD has been added.");
        
    }

    public void printCatalog() {
        if (this.isEmpty()) {
            System.out.println("number of CD here: " + this.size());
        } else {
            System.out.println("number of CD here: " + this.size());
            for (CD c : this) {
                c.print();
            }
        }
    }

    public void printAllCD() {
        Collections.sort(this);
        if (this.isEmpty()) {
            System.out.println("Empty list");
        } else {
            for (CD c : this) {
                c.print();
            }
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void updateCD() {
        String nameCD;
        String typeCD;
        String titleCD;
        double priceCD;
        String idCD;
        int publicYear;
        int pos;
        do { 
            nameCD = Mytool.readNonBlank("Enter CD name(game/movie/music): ");
            switch (nameCD) {
                case "game":
                    checkName++;
                    break;
                case "movie":
                    checkName++;
                    break;
                case "music":
                    checkName++;
                    break;
            }
        } while (nameCD.isEmpty());

        do { 
            typeCD = Mytool.readNonBlank("Enter type CD(audio/video): ");
            switch (typeCD) {
                case "audio":
                    checkCDT++;
                    break;
                case "video":
                    checkCDT++;
                    break;
                default:
                    System.out.println("Please put in right format.");
                    break;
            }
        } while (checkCDT == 0 && searchCD(typeCD));

        do {
            titleCD = Mytool.readPattern("Enter CD Title: ", CD_PATTERN);
            if (searchCD(typeCD)) {
                System.out.println("CD name is duplicated!");
            }
        } while (searchCD(titleCD));
        priceCD = Mytool.readRangeDouble("Price of new CD", 0, 10000);
        do {
            idCD = Mytool.readPattern("New CD ID(Cxxx): ", ID_FORMAT);
            idCD = idCD.toUpperCase();
            pos = checkID(idCD);
            if (pos >= 0) {
                System.out.println("Product ID is duplicated!");
            }
        } while (pos >= 0);
        publicYear = Mytool.readPublicYear("Enter public year: ");
        CD p = new CD(nameCD, typeCD, titleCD, priceCD, idCD, publicYear);
        this.add(p);
        System.out.println("New CD has been added.");
        changed = true;
    }

    public void deleteCD() {
        String productID = Mytool.readPattern("Enter ID needs removing: ", ID_FORMAT);
        int pos = checkID(productID);
        if (pos < 0) {
            System.out.println("Not found!");
        } else {
            CD deC = this.get(pos);
            remove(deC);
        }
        changed = true;
    }

    public void writeCDToFile() throws ParseException {
        if (changed) {
            Mytool.writeFile(dataFile, this);
            changed = false;
            System.out.println("CD save successfully");
            loadProductFromFile();
        }
    }
}
