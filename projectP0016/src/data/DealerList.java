
package data;

import java.util.ArrayList;
import java.util.List;
import mng.LogIn;
import tools.MyTool;


public class DealerList extends ArrayList<Dealer>{
    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    public static final String ID_FORMAT = "D\\d{3}";
    private String dataFile = "";
    boolean changed = false;
    
    public DealerList(LogIn loginObj){
        super();
        this.loginObj = loginObj; 
    }
    public void initWithFile(){
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }
    
    private void loadDealerFromFile(){
        List<String> lines = MyTool.readLinesFromFile(dataFile);
        for(String line:lines){
            String[] parts = line.split(Dealer.SEPARATOR);
            this.add(new Dealer(parts[0],parts[1], parts[2], parts[3], MyTool.parseBool(parts[4])));
        }
    }
    
    public DealerList getContinuingList(){
        DealerList list = new DealerList(loginObj);
        for(Dealer dealer : this){
            if(dealer.isContinuing())
                list.add(dealer);
        }
        return list;
    }
    public DealerList getUnContinuingList(){
        DealerList list = new DealerList(loginObj);
        for(Dealer dealer : this){
            if(!dealer.isContinuing())
                list.add(dealer);
        }
        return list;
    }
    private Dealer searchDealer(String ID){
        for(Dealer d : this)
            if(d.getID().equalsIgnoreCase(ID))
                return d;
        return null;
    }
    public void searchDealer(){
        Dealer d = searchDealer(MyTool.readPattern("Input ID:", ID_FORMAT));
        if(d!=null)
            System.out.println("Result: "+d);
        else
            System.out.println("NOT FOUND!");
    }
    public void addDealer(){
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        Dealer d;
        do{
            ID = MyTool.readPattern("ID of new dealer (D000): ","D\\d{3}").toUpperCase();
            if((d=searchDealer(ID))!=null)
                System.out.println("ID is duplicated!");
        }while(d!=null);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", PHONEPATTERN);
        continuing = true;
        this.add(new Dealer(ID, name, addr, phone, continuing));
        System.out.println("New dealer has been added!");
        changed = true;
    }
    public void removeDealer(){
        String ID = MyTool.readPattern("ID of dealer: ",ID_FORMAT);
        Dealer d = searchDealer(ID);
        if(d==null){
            System.out.println("NOT FOUND!");
            return;
        }
        d.setContinuing(false);
        changed = true;
        System.out.println("Dealer has been un-continuing!");
    }
    public void updateDealer(){
        String ID = MyTool.readPattern("Dealer's ID needs updating: ",ID_FORMAT);
        Dealer d = searchDealer(ID);
        if(d==null){
            System.out.println("NOT FOUND!");
            return;
        }
        System.out.println("New name, Enter for skip: ");
        String newName = MyTool.SC.nextLine().trim().toUpperCase();
        if(!newName.isEmpty()){
            d.setName(newName);
            changed = true;
        }
        System.out.println("New address, Enter for skip: ");
        String newAddr = MyTool.SC.nextLine().trim();
        if(!newAddr.isEmpty()){
            d.setAddr(newAddr);
            changed = true;
        }
        System.out.println("New phone number, Enter for skip: ");
        String newPhone = MyTool.SC.nextLine().trim();
        if(!newPhone.isEmpty()){
            if(!newPhone.matches(PHONEPATTERN))
                newPhone = MyTool.readPattern("Phone number require 9/11 digits:", PHONEPATTERN);
            d.setPhone(newPhone);
            changed = true;
        }
    }
    
    public void printAllDealers(){
        if(this.isEmpty())System.out.println("Empty list!");
        else System.out.println(this);
    }
    public void printCoutinuingDealers(){
        getContinuingList().printAllDealers();
    }
    public void printUncontinuingDealers(){
        getUnContinuingList().printAllDealers();
    }
    public void writeDealerToFile(){
        if(changed){
            MyTool.writeFile(dataFile, this);
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

