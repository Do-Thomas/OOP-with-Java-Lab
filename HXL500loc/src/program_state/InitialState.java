
package program_state;

import java.util.Scanner;
import model.User;
import service.ServiceDiscovery;
import service.UserService;
import storage.Messages;


public class InitialState implements PState{
     private static final String STATE_TITLE = "----LOGIN----";
    private static final String COMMAND = "Please login with username/password:";
    private static final String SEPARATOR = "/";
    private UserService userService;

    public InitialState() {
    }

    @Override
    public PState run() {
        return login();
    }

    private PState login() {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.println(STATE_TITLE);
            System.out.println(COMMAND);
            String credential = myObj.nextLine();
            String[] tmp = credential.split(SEPARATOR);
            if (tmp.length == 2) {
                PState nextState = null;
                // check username/password
                User user = ServiceDiscovery.userService.check(tmp[0], tmp[1]);
                if (user != null) {
                    // log in success
                    switch (user.getRole()) {
                        case R001: {
                            nextState = new R001State();
                            break;
                        }
                        case R002: {
                            nextState = new R002State();
                            break;
                        }
                        case ADMIN: {
                            nextState = new AdminState();
                            break;
                        }
                    }
                } else {
                    System.out.println(Messages.INCORRECT_USERNAME_PASSWORD);
                }
                if (nextState != null) return nextState;
            } else {
                System.out.println(Messages.INVALID_USERNAME_PASSWORD);
            }
        }
    }
}
