
package data;

import java.util.List;
import tools.MyTool;

public class Config {
    private static final String CONFIG_FILE = "Product/Config.txt";
    private String productFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("PRODUCT") >= 0) {
                productFile = "Product/" + parts[1].trim();
            }
        }
    }

    public String getProductFile() {
        return productFile;
    }
}
