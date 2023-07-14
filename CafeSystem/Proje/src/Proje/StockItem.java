package Proje;

public class StockItem {
    //unique
    private int id;

    private String name;

    private float amount;

    private String amountType;

    public StockItem(int id, String name, float amount, String amountType) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.amountType = amountType;
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", amountType='" + amountType + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public String getInfo(){
        return name + "\t\t => \t"+ amount + " " + amountType;

    }
}
