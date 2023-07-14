package Proje;

import java.util.Date;

public class BudgetItem {

    private int id;
    private String type;
    private float amount;

    private Date happenedDate;

    public BudgetItem(int id, String type, float amount, Date happenedDate) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.happenedDate = happenedDate;
    }

    @Override
    public String toString() {
        return "BudgetItem{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", happenedDate=" + happenedDate +
                '}';
    }
    public String getInfo(){

        return happenedDate + " => " + type + " => " + amount;


    }

    public float getAmount() {
        return amount;
    }


}