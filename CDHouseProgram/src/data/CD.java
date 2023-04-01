package data;

import Tool.Mytool;
import java.text.ParseException;

public class CD implements Comparable<CD>{

    private String nameCD;
    private String typeCD;
    private String titleCD;
    private double priceCD;
    private String idCD;
    private int publicYear;
    public static final char SEPARATOR = ',';

    public CD() {
    }

    public CD(String nameCD, String typeCD, String titleCD, double priceCD, String idCD, int publicYear) {
        this.nameCD = nameCD;
        this.typeCD = typeCD;
        this.titleCD = titleCD;
        this.priceCD = priceCD;
        this.idCD = idCD;
        this.publicYear = publicYear;
    }

    CD(String line) throws ParseException {
        String[] parts = line.split("" + CD.SEPARATOR);
        nameCD = parts[0].trim();
        typeCD = parts[1].trim();
        titleCD = parts[2].trim();
        priceCD = Mytool.parseDouble(parts[3]); //parse double
        idCD = parts[4].trim();
        publicYear = Mytool.parseInt(parts[5]); //parse int
    }

    public String getTitleCD() {
        return titleCD;
    }

    public void setTitleCD(String titleCD) {
        this.titleCD = titleCD;
    }

    public String getNameCD() {
        return nameCD;
    }

    public void setNameCD(String nameCD) {
        this.nameCD = nameCD;
    }

    public String getTypeCD() {
        return typeCD;
    }

    public void setTypeCD(String typeCD) {
        this.typeCD = typeCD;
    }

    public double getPriceCD() {
        return priceCD;
    }

    public void setPriceCD(float priceCD) {
        this.priceCD = priceCD;
    }

    public String getIdCD() {
        return idCD;
    }

    public void setIdCD(String idCD) {
        this.idCD = idCD;
    }

    public int getPublicYear() {
        return publicYear;
    }

    public void setPublicYear(int publicYear) {
        this.publicYear = publicYear;
    }

    public void print() {
        System.out.printf("%-7s|%-7s|%-28s|%-10s|%-6s|%7s\n", nameCD, typeCD, titleCD,priceCD, idCD, publicYear);
    }

    @Override
    public int compareTo(CD o) {
        return this.getTitleCD().compareToIgnoreCase(o.getTitleCD());
    }

    @Override
    public String toString() {
        return nameCD + ", " + typeCD + ", " + titleCD + ", " + priceCD + ", " + idCD + ", " + publicYear;
    }


}
