package Proje;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabaseConnection {

    String user="postgres";
    String password="202055"; // #CHANGE THIS VALUE TO YOUR OWN
    Statement s;
    Connection connection;

    public DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/Cafe",
                            user, password);
            this.s = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }


    public ArrayList<MenuOption> getAllMenu() throws SQLException {
        ArrayList<MenuOption> menu=new ArrayList<MenuOption>();

        String SQL = "SELECT * FROM menu";
        ResultSet set =s.executeQuery(SQL);

        while(set.next()){

            menu.add(new MenuOption(set.getInt(1),set.getString(2),set.getFloat(3),
                    set.getString(4),set.getArray(5)));

        }

        return menu;
    }

    public ArrayList<StockItem> getAllStockItems() throws SQLException{
        ArrayList<StockItem> items=new ArrayList<StockItem>();

        String SQL = "SELECT * FROM stock";
        ResultSet set= s.executeQuery(SQL);
        while (set.next()){
            items.add(new StockItem(set.getInt(1),set.getString(2),set.getFloat(3),
                    set.getString(4)));


        }

        return items;
    }


    public ArrayList<Worker> getAllWorkers() throws SQLException{

        ArrayList<Worker> workers= new ArrayList<Worker>();

        String SQL = "SELECT * FROM workers";
        ResultSet set= s.executeQuery(SQL);
        while (set.next()) {
            workers.add(new Worker(set.getInt(1),set.getString(2),set.getString(3),
                    set.getString(4),set.getFloat(5),set.getDate(6)));
        }

        return workers;

    }
    public ArrayList<BudgetItem> getAllBudgetItems()throws SQLException{
        ArrayList<BudgetItem> items=new ArrayList<BudgetItem>();

        String SQL = "SELECT * FROM budget";

        ResultSet set=s.executeQuery(SQL);
        while (set.next()){
            items.add(new BudgetItem(set.getInt(1),set.getString(2),set.getFloat(3),
                    set.getDate(4)));

        }

        return items;
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        ArrayList<Order> orders= new ArrayList<Order>();

        String SQL= "SELECT * from orders";

        ResultSet set=s.executeQuery(SQL);

        while (set.next()){

            orders.add(new Order(set.getInt(1),set.getInt(2),set.getDate(3).toLocalDate(),
                    set.getTime(4).toLocalTime(),set.getFloat(5),set.getArray(6)));

        }

        return orders;
    }
    public ArrayList<Order> getAllOrders(String date) throws SQLException {
        ArrayList<Order> orders= new ArrayList<Order>();

        String SQL= "SELECT * from orders where order_date =\'"+date+"\'";

        ResultSet set=s.executeQuery(SQL);

        while (set.next()){

            orders.add(new Order(set.getInt(1),set.getInt(2),set.getDate(3).toLocalDate(),
                    set.getTime(4).toLocalTime(),set.getFloat(5),set.getArray(6)));

        }

        return orders;
    }
    public ArrayList<Order> getAllOrders(int tableNo) throws SQLException {
        ArrayList<Order> orders= new ArrayList<Order>();

        String SQL= "SELECT * from orders where order_table =\'"+tableNo+"\'";

        ResultSet set=s.executeQuery(SQL);

        while (set.next()){

            orders.add(new Order(set.getInt(1),set.getInt(2),set.getDate(3).toLocalDate(),
                    set.getTime(4).toLocalTime(),set.getFloat(5),set.getArray(6)));

        }

        return orders;
    }

    public ArrayList<Order> getAllOrders(String date,int tableNo) throws SQLException {
        ArrayList<Order> orders= new ArrayList<Order>();

        String SQL= "SELECT * from orders where order_date =\'"+date+"\' AND order_table=\'"+tableNo+"\'";

        ResultSet set=s.executeQuery(SQL);

        while (set.next()){

            orders.add(new Order(set.getInt(1),set.getInt(2),set.getDate(3).toLocalDate(),
                    set.getTime(4).toLocalTime(),set.getFloat(5),set.getArray(6)));

        }

        return orders;
    }

    public MenuOption getMenuOption(int itemId) throws SQLException {
        MenuOption option=null;
        String SQL="SELECT * FROM menu where item_id="+itemId;
        ResultSet set=s.executeQuery(SQL);
        while (set.next()){

            option=new MenuOption(set.getInt(1),set.getString(2),set.getFloat(3),
                    set.getString(4),set.getArray(5));

        }

        return option;
    }

    public void insertPayment(String type,float amount,String date) throws SQLException {

        String SQL="insert into budget(item_type,item_amount ,item_happened_date) " +
                "values(\'"+type +"\',"+amount+",\'"+date+"')";
        s.executeUpdate(SQL);

    }

    public void paySalaries(String date) throws SQLException {

        ArrayList<Worker> workers=getAllWorkers();

        float totalAmount=0;

        for(Worker i:workers){

            totalAmount+=i.getSalary();

        }

        insertPayment("maaş ödemesi",-totalAmount,date);


    }

    public void updateNewStockItemAmount(int stockItemId,float amount)throws SQLException{
        String SQL="UPDATE stock SET item_amount="+amount+"WHERE item_id="+stockItemId;
        s.executeUpdate(SQL);

    }

    public void updateStockItemAmount(int stockItemId,float amount) throws SQLException {
        System.out.println(amount);
        String SQL="UPDATE stock SET item_amount=item_amount-"+String.valueOf(amount)+" WHERE item_id="+stockItemId;
        s.executeUpdate(SQL);
    }

    public float getTotalBudget() throws SQLException {
        ArrayList<BudgetItem> budgetItems=getAllBudgetItems();
        float totalSum=0;

        for(BudgetItem i:budgetItems){

            totalSum+=i.getAmount();
        }

        return totalSum;
    }

    public void insertOrder(Order order) throws SQLException {
        String SQL="insert into orders(order_table,order_date,order_time,order_total_amount,order_list) " +
                "values("+order.getTableNo()+",\'"+order.getOrderDate()+"\',\'"+order.getOrderTime()+"\',"+order.getOrderTotalAmount()+"," +
                "\'"+order.getOrders()+"\')";

        Integer[] orders=(Integer[])order.getOrders().getArray();

        for(int i:orders){

            MenuOption option=getMenuOption(i);

            for(float[] x: option.getIngredients()){

                System.out.println(x[0]+"  "+x[1]);
                updateStockItemAmount((int)x[0],x[1]);

            }


        }

        s.executeUpdate(SQL);

        insertPayment("sipariş",order.getOrderTotalAmount(),order.getOrderDate().toString());

    }

    public Array convertIntArray(int [] array) throws SQLException {

        Object[] values = Arrays.stream(array).boxed().toArray();
        Array a= connection.createArrayOf("int",values);
        return a;

    }

}
