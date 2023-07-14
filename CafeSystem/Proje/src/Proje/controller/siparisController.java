package Proje.controller;

import Proje.DatabaseConnection;
import Proje.MenuOption;
import Proje.Order;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Proje.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class siparisController {

    private DatabaseConnection db;
    private ArrayList<MenuOption> menuList;
    private ArrayList<Order> orderList;

    private ObservableList<Integer> MasaNumaraList;

    private ArrayList<Integer> selectedItems;
    private int selectedTable;

    private float toplamTutar;

    @FXML
    private TextArea siparisText = null;

    @FXML
    private TextField masaNotext = null;

    @FXML
    private TextArea toplamTutarText = null;

    @FXML
    private Button siparisGecmis = null;

    @FXML
    private Button yiyecekEkle = null;

    @FXML
    private ComboBox comboY;

    @FXML
    private ComboBox comboI;

    @FXML
    private ComboBox comboM;


    public siparisController() throws SQLException {

        db=new DatabaseConnection();
        menuList = db.getAllMenu();
        orderList = db.getAllOrders();

        selectedItems=new ArrayList<Integer>();
        MasaNumaraList = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        toplamTutar = 0;
    }

    public ObservableList<String> getIceceklerList() throws SQLException{

        ObservableList<String> IceceklerList = FXCollections.observableArrayList();

        for(MenuOption icecekler: menuList){

            if(icecekler.getType().equals("içecek")){

                IceceklerList.add(icecekler.getName());
            }

        }
        return IceceklerList;
    }

    public ObservableList<String> getYiyeceklerList() throws SQLException {

        ObservableList<String> YiyeceklerList = FXCollections.observableArrayList();

        for(MenuOption yiyecekler: menuList){

            if(yiyecekler.getType().equals("yiyecek")){

                YiyeceklerList.add(yiyecekler.getName());
            }
        }
        return YiyeceklerList;
    }

    public float getFiyat(String ad){

        float fiyat = 0;
        for(MenuOption fiyatlar: menuList){

            if(fiyatlar.getName().equals(ad)){

                fiyat = fiyatlar.getPrice();
            }
        }
        return fiyat;
    }

    @FXML
    public void initialize() throws SQLException {

        comboM.setItems(MasaNumaraList);
        comboY.setItems(getYiyeceklerList());
        comboI.setItems(getIceceklerList());
    }

    @FXML
    public void SelectM(ActionEvent event){

        String s = comboM.getSelectionModel().getSelectedItem().toString();
        selectedTable=Integer.parseInt(s);
        masaNotext.insertText(0, s);
    }


    @FXML
    public void SelectY(ActionEvent event){}


    public void yiyecekEkleClicked(MouseEvent mouseEvent) {

        String s = comboY.getSelectionModel().getSelectedItem().toString();

        MenuOption selected=null;
        for(MenuOption i: menuList){

            if(i.getName().equals(s)){
                selected=i;

            }

        }

        selectedItems.add(selected.getId());

        siparisText.insertText(0,s + " => Fiyat=> " + getFiyat(s) + " TL" + "\n");
        toplamTutar += getFiyat(s);

        toplamTutarText.clear();
        toplamTutarText.insertText(0,"Toplam Tutar => " + toplamTutar);

    }

    @FXML
    public void icecekEkleClicked(MouseEvent mouseEvent){
        String s = comboI.getSelectionModel().getSelectedItem().toString();

        MenuOption selected=null;
        for(MenuOption i: menuList){

            if(i.getName().equals(s)){
                selected=i;

            }

        }

        selectedItems.add(selected.getId());

        siparisText.insertText(0,s + " => Fiyat=> " + getFiyat(s) + " TL" + "\n");
        toplamTutar += getFiyat(s);

        toplamTutarText.clear();
        toplamTutarText.insertText(0,"Toplam Tutar => " + toplamTutar);

    }

    @FXML
    public void SelectI(ActionEvent event){

    }

    public void siparisgecmisClicked(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proje/resources/siparisGecmis.fxml"));
            Parent root = loader.load();

            Main.getSiparisGecmisStage().setScene(new Scene(root));
            Main.getSiparisGecmisStage().show();
        }
        catch(IOException ex){

            System.out.println(ex);
        }
    }


    public void siparisTamamlaClicked(MouseEvent mouseEvent) throws SQLException {



        // toplam tutarı bütçeye gönder
        int[] arr = selectedItems.stream().mapToInt(i -> i).toArray();
        db.insertOrder(new Order(selectedTable, LocalDate.now(), LocalTime.now(),toplamTutar,db.convertIntArray(arr)));

        successAlert(toplamTutar);
        masaNotext.clear();
        siparisText.clear();
        toplamTutarText.clear();
        toplamTutar = 0;
        selectedTable=0;
        selectedItems=new ArrayList<Integer>();

    }

    private void successAlert(float totalAmount){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgi");
        alert.setHeaderText("Sipariş başarı ile kaydedildi");
        String text="Toplam Tutar="+totalAmount;
        alert.setContentText(text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {

            }
        });

    }


}
