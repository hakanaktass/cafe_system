package Proje.controller;

import Proje.DatabaseConnection;
import Proje.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class siparisGecmisController {

    public DatePicker datePicker;
    DatabaseConnection db=new DatabaseConnection();
    @FXML
    private TextArea gecmisText = null;

    @FXML
    private ComboBox comboGM = null; //GM = geçmiş masa numaraları

    @FXML
    private ComboBox comboT = null;

   

    @FXML
            private Button masaSecmeButton=null;
    @FXML
            private Button belirtilenZamanButton=null;
    @FXML
            private Button belirtilenMasaBelirtilenZamanButton=null;

    
    ObservableList<Integer> gecmisMasaNo = FXCollections.observableArrayList(1,2,3,4,5);

    @FXML
    public void initialize(){

        comboGM.setItems(gecmisMasaNo);

    }

    public void SelectGM(ActionEvent event){}

    @FXML
    public void masaSecili(MouseEvent mouseEvent) throws SQLException {
        db.getAllOrders((int) comboGM.getSelectionModel().getSelectedItem());

        StringBuilder s= new StringBuilder();

        for(Order o:db.getAllOrders((int) comboGM.getSelectionModel().getSelectedItem())){
            s.append(o.bilgi());
            s.append("\nSiparişler=> ");

            for(int x:(Integer[])o.getOrders().getArray()){

                s.append(db.getMenuOption(x).getName());
                s.append(", ");

            }
            s.append("\n************************\n");


        }

        gecmisText.setText(s.toString());

    }


    public void belirtilenZaman(MouseEvent mouseEvent) throws SQLException {



        StringBuilder s= new StringBuilder();

        for(Order o:db.getAllOrders(datePicker.getValue().toString())){
            s.append(o.bilgi());
            s.append("\nSiparişler=> ");

            for(int x:(Integer[])o.getOrders().getArray()){

                s.append(db.getMenuOption(x).getName());
                s.append(", ");

            }
            s.append("\n************************\n");


        }

        gecmisText.setText(s.toString());
    }

    public void belirtilenMasaBelirtilenZaman(MouseEvent mouseEvent) throws SQLException {


        StringBuilder s= new StringBuilder();

        for(Order o: db.getAllOrders(datePicker.getValue().toString(),(int) comboGM.getSelectionModel().getSelectedItem())){
            s.append(o.bilgi());
            s.append("\nSiparişler=> ");

            for(int x:(Integer[])o.getOrders().getArray()){

                s.append(db.getMenuOption(x).getName());
                s.append(", ");

            }
            s.append("\n************************\n");


        }

        gecmisText.setText(s.toString());
    }
}
