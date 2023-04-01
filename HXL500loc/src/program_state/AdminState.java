/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_state;

import java.util.Scanner;
import storage.Messages;

/**
 *
 * @author Asus
 */
public class AdminState  implements PState {
    private static final String STATE_TITLE = "----ADMIN----";
    private static final String MSG = "This module is not available!";
    private static final String OPTION_0 = "0-Logout";

    public AdminState() {
    }

    @Override
    public PState run() {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            System.out.println(STATE_TITLE);
            System.out.println(OPTION_0);
            System.out.println(SELECT_ONE_OPTION);
            String selection = myObj.nextLine();
            PState nextState = null;
            switch (selection) {
                case "0": {
                    nextState = new InitialState();
                    break;
                }
                default: {
                    System.out.println(Messages.NOT_AVAILABLE_OPTION);
                }
            }
            if (nextState != null) return nextState;
        }
    }
}
