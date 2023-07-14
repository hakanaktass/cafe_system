package Proje.controller;

import Proje.DatabaseConnection;
import Proje.StockItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class stokGuncellemeController {

    DatabaseConnection db=new DatabaseConnection();
    @FXML
    public TextField malMiktarText=null;

    @FXML
    public Text miktarBirim=null;

    @FXML
    public Button malzemeGuncelleButton=null;


    @FXML
    private ComboBox comboMal = null;

    // serhatın verdiği data tipine göre değiştirilebilir
    ObservableList<String> malzemelerList = FXCollections.observableArrayList();

    ArrayList<StockItem> items;


    public void initialize() throws SQLException {

        items=db.getAllStockItems();

        for(StockItem i:items){

            malzemelerList.add(i.getName());

        }

        comboMal.setItems(malzemelerList);


    }



    public void SelectMal(ActionEvent actionEvent) {

        String s = comboMal.getSelectionModel().getSelectedItem().toString();

        StockItem item=findItem(s);


        malMiktarText.setText(String.valueOf(item.getAmount()));
        miktarBirim.setText(item.getAmountType());

        //System.out.println(s);
    }

    public void malzemeGuncelle(MouseEvent mouseEvent) throws SQLException {

        String s = comboMal.getSelectionModel().getSelectedItem().toString();

        StockItem item=findItem(s);

        db.updateNewStockItemAmount(item.getId(),Float.parseFloat(malMiktarText.getText()));

        successAlert(Float.parseFloat(malMiktarText.getText()),item.getAmountType());

    }

    public StockItem findItem(String name){

        for(StockItem i:items){

            if(i.getName().equals(name)){

                return i;
            }

        }

        return null;

    }

    private void successAlert(float totalAmount,String amountType){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgi");
        alert.setHeaderText("Yeni miktar başarı ile kaydedildi");
        String text=totalAmount+" "+amountType;
        alert.setContentText(text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {

            }
        });

    }
}
