package Proje;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    private static Stage siparisStage = null;
    private static Stage finansStage = null;
    private static Stage stokStage = null;
    private static Stage siparisGecmisStage = null;
    private static Stage stokGuncellemeStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("MENU");

        Parent menuRoot = FXMLLoader.load(getClass().getResource("Resources/menu.fxml"));
        Scene menu = new Scene(menuRoot);

        primaryStage.setScene(menu);
        primaryStage.show();

        createFinansStage();
        createSiparisStage();
        createStokStage();
        createSiparisGecmisStage();
        createStokGuncellemeStage();
    }

    public void createStokGuncellemeStage(){

        stokGuncellemeStage = new Stage();
        stokGuncellemeStage.setTitle("Stok Guncelleme");
    }

    public static Stage getStokGuncellemeStage() {
        return stokGuncellemeStage;
    }

    public void createSiparisGecmisStage(){

        siparisGecmisStage = new Stage();
        siparisGecmisStage.setTitle("Sipariş Geçmişi");
    }

    public static Stage getSiparisGecmisStage() {
        return siparisGecmisStage;
    }

    public void createStokStage(){

        stokStage = new Stage();
        stokStage.setTitle("Stok");
    }

    public static Stage getStokStage() {
        return stokStage;
    }

    public void createFinansStage(){

        finansStage = new Stage();
        finansStage.setTitle("Finans");

    }

    public static Stage getFinansStage() {
        return finansStage;
    }

    public void createSiparisStage(){

        siparisStage = new Stage();
        siparisStage.setTitle("Sipariş");
    }

    public static Stage getSiparisStage() {
        return siparisStage;
    }

    public static void main(String[] args) throws SQLException {
        launch(args);

        //DatabaseConnection a=new DatabaseConnection();
        //a.paySalaries("2020-11-01");

        //a.updateStockItemAmount(1, (float) 5.2);

        /*System.out.println(a.getAllMenu());
        System.out.println(a.getAllStockItems());
        System.out.println(a.getAllWorkers());
        System.out.println(a.getAllBudgetItems());

        System.out.println(a.getAllOrders());
        System.out.println(a.getAllOrders("2017-11-06"));
        System.out.println(a.getAllOrders(1));
        System.out.println(a.getAllOrders("2017-11-06",1));
        System.out.println(a.getTotalBudget());*/

        //a.insertOrder(new Order(3, LocalDate.now(), LocalTime.now(),(2), a.convertIntArray(new int[]{1})));
    }
}
