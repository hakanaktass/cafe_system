package Proje;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuOption {
    //unique olmalı
    private int id;
    private String name;
    private float price;
    //yiyecek mi içecekmi onu ekle
    private String type;

    //first for id second for amount
    private Array ingredients;

    public MenuOption(int id, String name, float price, String type, Array ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.ingredients=ingredients ;
    }

    @Override
    public String toString() {
        return "MenuOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    public float[][] getIngredients() throws SQLException {
        Object[] arrays=(Object[])ingredients.getArray();
        int counter=0;
        float[][] list=new float[arrays.length][2];
        for(Object i:arrays){
            Double[] a=(Double[])i;
            float x=a[0].floatValue();
            float y=a[1].floatValue();
            list[counter][0]=x;
            list[counter][1]=y;
            counter++;
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
