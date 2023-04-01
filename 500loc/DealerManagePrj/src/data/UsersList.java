package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import tools.MyTools;

/**
 *
 * @author Asus
 */
public class UsersList extends ArrayList<Users> {
    private String userFile;
    String filename;
    private String usersFile;
    private static String SEPARATOR = "\\|";
    private Users user = null;
    
 
    private void setUsersFile() {
        Config cR = new Config();
        usersFile = cR.getUsersFile();
    }

    public enum Role {
        R001("R001"), R002("R002"), ADMIN("ADMIN");
        public final String role;

        Role(String role) {
            this.role = role;
        }
    }

    public enum Status {
        ENABLED("Enabled"), DISABLED("Disabled");
        public final String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    public boolean check(Users user) {
        List<String> lines = MyTools.readLinesFromFile(userFile);
        for (String line : lines) {
            String[] parts = line.split(this.SEPARATOR);
            if (parts.length < 4) {
                return false;
            }
            if (parts[0].equalsIgnoreCase(user.getName())
                    && parts[1].equals(user.getPassword())
                    && parts[2].equals(user.getRole())
                    && parts[3].equals(user.getStatus())) {
                return true;
            }
        }
        return false;
    }


    public static List<Dealer> readDealers(String name) throws IOException {
        Path path = Paths.get(name);
        List<String> lines = Files.readAllLines(path);
        List<Dealer> dealers = new ArrayList<Dealer>();
        boolean firstLine = true;
        for (String line : lines) {
            // skip the first line
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] comps = line.split(SEPARATOR);
            if (comps.length == 5) {
//                String status = Status.valueOf(comps[4].toUpperCase());
//                Dealer dealer = new Dealer(comps[0], comps[1], comps[2], comps[3]);
//                dealers.add(dealer);
            }
        }
        return dealers;
    }

    public static void main(String[] args) {
        UsersList aChk = new UsersList();
        Users acc = new Users("john", "h#56iOq1", "R001");
        boolean valid = aChk.check(acc);
        System.out.println("Needs ok, ok? " + valid);
        acc = new Users("mary", "23D#iEq2", "R002");
        valid = aChk.check(acc);
        System.out.println("Needs ok, ok? " + valid);
        acc = new Users("oliver", "opT67B%a", "ADMIN");
        valid = aChk.check(acc);
        System.out.println("Needs no ok, ok? " + valid);
    }
}
