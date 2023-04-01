
package data;

import java.util.List;
import tools.MyTool;

public class Config {
    private static final String CONFIG_FILE = "DealerData/config.txt";
    private static final String FOLDER_NAME = "DealerData/";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;
    
    
    public Config(){
        readData();
    }
    private void readData(){
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for(String line : lines){
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if(line.indexOf("ACCOUN")>=0)
                accountFile = FOLDER_NAME+parts[1].trim();
            else if(line.indexOf("DEALE")>=0)
                dealerFile = FOLDER_NAME+parts[1].trim();
            else if(line.indexOf("DELIVER")>=0)
                deliveryFile = FOLDER_NAME+parts[1].trim();
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
