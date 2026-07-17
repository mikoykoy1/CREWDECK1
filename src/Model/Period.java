/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer Ryzen 5
 */
public enum Period {
    Q1("1st Quarter"),
    Q2("2nd Quarter"),
    Q3("3rd Quarter"),
    Q4("4th Quarter"),
    MID_YEAR("Mid-Year"),
    ANNUAL("Annual");

    private final String displayName;

    Period(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName; 
    }
}
