package main;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Dealer;
import model.User;
import program_state.InitialState;
import program_state.PState;
import service.DealerService;
import service.DealerServiceImpl;
import service.ServiceDiscovery;
import service.UserService;
import service.UserServiceImpl;
import storage.DB;
import util.ConfigReader;
import util.CsvReader;

//main

public class App {

    public static void main(String[] args) throws IOException {
        ConfigReader.Config config = ConfigReader.read();
        List<User> users = CsvReader.readUsers(config.userFile);
        Map<String, User> userMap = new HashMap<String, User>();
        for (User user : users) {
            userMap.put(user.getUsername(), user);
        }
        List<Dealer> dealers = CsvReader.readDealers(config.dealerFile);
        DB db = new DB(users, userMap, dealers, null);
        UserService userService = new UserServiceImpl(db);
        DealerService dealerService = new DealerServiceImpl(db);
        ServiceDiscovery.userService = userService;
        ServiceDiscovery.dealerService = dealerService;

        PState state = new InitialState();
        while (true) {
            state = state.run();
        }
    }
}
