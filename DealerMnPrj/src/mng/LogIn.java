/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import tools.Mytools;

public class LogIn {

    private Account acc = null;

    public LogIn(Account acc) {
        this.acc = acc;
    }

    public static Account inputAccount() {
         String accName = Mytools.readNonBlank("Enter user name: ");
        String pwd = "";
        pwd = Mytools.readNonBlank("Enter password: ");
        String role = Mytools.readNonBlank("Enter role: ");
        return new Account(accName, pwd, role);
    }

    public Account getAcc() {
        return acc;
    }

    public static void main(String[] args) {
        Account acc = null;
        boolean cont = false;
        boolean valid = false;
        do {
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount();
            valid = accChk.check(acc);
            if (!valid) {
                cont = Mytools.readBool("Invalid account- Try again?");
            }
            if (!valid && !cont) {
                System.exit(0);
            }
        } while (cont);
        LogIn loginObj = new LogIn(acc);
        if (acc.getRole().equalsIgnoreCase("ACC-1")) {
            String[] options = {"Add new dealer\n", "Search a dealer\n", "Remove a dealer\n",
                "Update a dealer\n", "Print all dealers\n", "Print continuing dealers\n",
                "Print UN-continuing dealers\n", "Write to file\n"};
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj);
            dList.initWithFile();
            int choice = 0;
            do {
                choice = mnu.getChoice(options);
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
                        dList.printContinuingDealers();
                        break;
                    case 7:
                        dList.printUnContinuingDealers();
                        break;
                    case 8:
                        dList.writeDealerToFile();
                        break;
                    default:
                        if (dList.isChanged()) {
                            boolean res = Mytools.readBool("Data changed. Write to file?");
                            if (res == true) {
                                dList.writeDealerToFile();
                            }
                        }
                }
            } while (choice > 0 && choice < mnu.size());
            System.out.println("Bye");
        }
    }
}
