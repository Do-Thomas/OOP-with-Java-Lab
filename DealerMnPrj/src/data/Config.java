/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import tools.Mytools;

public class Config {

    private static final String CONFIG_FILE = "DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = Mytools.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUN") >= 0) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DEALE") >= 0) {
                dealerFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DEALE") >= 0) {
                deliveryFile = "DealerData/" + parts[1].trim();
            } else if (line.indexOf("DEALE") >= 0) {
                
            }
        }
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }
}
