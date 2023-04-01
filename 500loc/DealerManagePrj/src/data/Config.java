package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tools.MyTools;

/**
 *
 * @author Asus
 */
public class Config {

    private static final String CONFIG_FILE = "resource/config.txt";
    private static final String USER_FILE = "resource/users.txt";
    private static final String DEALER_FILE = "resource/dealers.txt";
    
    private static final String USER_FILE_KEY = "resource/users_file";
    private static final String DEALER_FILE_KEY = "resource/dealers_file";
    private static final String DELIVERIES_FILE_KEY = "resource/deliveries_file";
    private String usersFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    public static class ConfigFile {

        public String usersFile;
        public String dealerFile;
        public String deliveryFile;
    }

    private static ConfigFile readData() {
        List<String> lines = MyTools.readLinesFromFile(CONFIG_FILE);
        InputStream is = App.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        InputStreamReader streamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(streamReader);
        ConfigFile config = new ConfigFile();
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split("|");
//            if (line.indexOf("USERS") >= 0) {
//                usersFile = "resource/" + parts[1].trim();
//            } else if (line.indexOf("DEALE") >= 0) {
//                dealerFile = "resource/" + parts[1].trim();
//            } else if (line.indexOf("DELIVERY") >= 0) {
//                deliveryFile = "resource/" + parts[1].trim();
//            }
            if (parts[0].equals(USER_FILE_KEY)) {
                config.usersFile = parts[1];
            } else if (parts[0].equals(DEALER_FILE_KEY)) {
                config.dealerFile = parts[1];
            } else if (parts[0].equals(DELIVERIES_FILE_KEY)) {
                config.deliveryFile = parts[1];
            }
        }
        return config;
    }

    public class App {
//        public static void main(String[] args) throws IOException {

//            ConfigFile config = readData();
//            List<Users> users = UsersList.readUsers(config.usersFile);
//            Map<String, Users> userMap = new HashMap<String, Users>();
//            
//            List<Dealer> dealers = UsersList.readDealers(dealerFile);
//        DB db = new DB(users, userMap, dealers, null);
//        UserService userService = new UserServiceImpl(db);
//        DealerService dealerService = new DealerServiceImpl(db);
//        ServiceDiscovery.userService  = userService;
//        ServiceDiscovery.dealerService  = dealerService;

//            PState state = new InitialState();
//
//            while (true) {
//                state = state.run();
//            }
        
    }

    public String getUsersFile() {
        return USER_FILE;
    }
    public String getDealerFile() {
        return DEALER_FILE;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }
}
