package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.MyTool;

public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
        String accName = MyTool.readNonBlank("Enter user name: ");
        String pwd = "";
        pwd = MyTool.readNonBlank("Enter password: ");
        String role = MyTool.readNonBlank("Enter role: ");
        return new Account(accName, pwd, role);
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont;
        boolean valid;
        AccountChecker accChk;
        do {
            cont = false;
            accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = MyTool.readBool("Invalid account - try again?");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        LogIn login = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer", "Search a dealer",
                "Remove a dealer", "Update a dealer",
                "Print all dealers", "Print continuing dealers",
                "Print Un-continuing dealers", "Write tp file", "Change password", "Quit"};
            Menu menu = new Menu(options);
            DealerList dList = new DealerList(login);
            dList.initWithFile();//load file
            int choice = 0;
            do {
                choice = menu.getChoice("Managin dealers");
                switch (choice) {
                    case 1:
                        dList.addDealer();
                        break;
                    case 2:
                        dList.searchDealer();
                        break;
                    case 3:
                        dList.removeDealer();
                        break;
                    case 4:
                        dList.updateDealer();
                        break;
                    case 5:
                        dList.printAllDealers();
                        break;
                    case 6:
                        dList.printCoutinuingDealers();
                        break;
                    case 7:
                        dList.printUncontinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    case 9:
                        accChk.changePassWord();
                        break;
                    case 10:
                        if (dList.isChanged() && MyTool.readBool("Are you want to save data before quit?")) {
                            dList.writeDealerToFile();
                        }
                        System.out.println("Have a good day!");
                        break;
                    default:
                        System.out.println("Please choice correct option!");
                        break;
                }
            } while (choice != 10);
        }
    }
}
