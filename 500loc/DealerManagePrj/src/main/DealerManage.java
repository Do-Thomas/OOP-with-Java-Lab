package main;

import data.AllEnum;
import data.DealerList;
import data.Users;
import data.UsersList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.net.ssl.SSLEngineResult;
import tools.MyTools;

public class DealerManage {

    Scanner sc = new Scanner(System.in);
    private Users user = null;

    public DealerManage(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public static Users inputUser() {
        boolean isvalid = true;
        String userName;
        String passwordUser;
        String roleUsers;
        do {
            userName = MyTools.readNonBlank("Username:");
            passwordUser = MyTools.readNonBlank("Password for user:");
            roleUsers = MyTools.readNonBlank("Role for user:");
        } while (!isvalid);
        return new Users(userName, passwordUser, roleUsers);
    }

    public static void main(String[] args) throws IOException {
        Users user = null;
        Users login = new Users(user);
        boolean cont = false;
        boolean valid = false;
        do {
            UsersList checkUser = new UsersList();
            user = inputUser();
            valid = checkUser.check(user);
            if (!valid) {
                cont = MyTools.parseBool("Users not exit!");
            }
            if (!cont) {
                System.exit(0);
            }
        } while (!valid);
        DealerList listD = new DealerList();
        listD.initWithFile();
        ArrayList<String> options = new ArrayList<>();
        if (user.getRole().equalsIgnoreCase("R001")) {
            options.add("1 - Add new dealer");
            options.add("2 - Search a dealer");
            options.add("3 - Remove a dealer");
            options.add("4 - Update a dealer");
            options.add("5 - Print all dealers");
            options.add("6 - Print continuing dealers");
            options.add("7 - Print UN-continuing dealers");
            options.add("8 - Write to file");
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            do {
                choice = Menu.int_getChoice(options);
                System.out.println("\nManaging dealers");
                switch (choice) {
                    case 1:
                        listD.addDealer();
                        break;
                    case 2:
                        String ID;
                        System.out.println("Enter Dealer ID to search: ");
                        ID = sc.nextLine();
                        listD.searchDealerID(ID);
                        break;
                    case 3:
                        listD.removeDealer();
                        break;
                    case 4:
                        listD.updateDealer();
                        break;
                    case 5:
                        listD.printAllDealers();
                        break;
                    case 6:
                        listD.printContinuingDealers();
                        break;
                    case 7:
                        listD.printUNcontinuingDealers();
                        break;
                    case 8:
                        listD.writeDealerToFile("dealers.txt");
                        break;
                }
            } while (choice > 0 && choice < 9);
            System.out.println("Bye!");
        } else {
            System.out.println("Invalid feature...");
            int choice = Menu.int_getChoice(options);
            do {
                switch (choice) {
                    case 1:
                        user.setRole(null);
                        break;
                }
            } while (choice != 1);
        }
    }
}
