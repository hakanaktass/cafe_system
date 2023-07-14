package Proje;

import java.sql.Array;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    //unique olmalı
    private int id;
    private int tableNo;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private float orderTotalAmount;

    //MenuOption ids
    private Array orders;

    public Order(int id, int tableNo, LocalDate orderDate, LocalTime orderTime, float orderTotalAmount, Array orders) {
        this.id = id;
        this.tableNo = tableNo;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderTotalAmount = orderTotalAmount;
        this.orders = orders;
    }

    public Order(int tableNo, LocalDate orderDate, LocalTime orderTime, float orderTotalAmount, Array orders) {
        this.tableNo = tableNo;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderTotalAmount = orderTotalAmount;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableNo=" + tableNo +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderTotalAmount=" + orderTotalAmount +
                ", orders=" + orders +
                '}';
    }

    public String bilgi(){
       return "Masa Numarası=> " + tableNo +
                "\nSipariş Günü=> " + orderDate +
                "\nSipariş Saati=> " + orderTime +
                "\nSipariş Miktarı=> " + orderTotalAmount;

    }

    public int getId() {
        return id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public float getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public Array getOrders() {
        return orders;
    }


}
