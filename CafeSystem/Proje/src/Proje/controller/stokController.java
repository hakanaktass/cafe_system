package Proje.controller;

import Proje.DatabaseConnection;
import Proje.StockItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import Proje.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class stokController {

    DatabaseConnection db=new DatabaseConnection();
    @FXML
    private Button stokGuncelleme = null;

    @FXML

    private Button mevcutMalzemeButton= null;

    @FXML
    private TextArea mevcutMalzemeText=null;

    public void stokGuncellemeClicked(MouseEvent mouseEvent) {openStokGuncellemeStage();}

    public void openStokGuncellemeStage(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proje/resources/stokGuncelleme.fxml"));
            Parent root = loader.load();

            Main.getStokGuncellemeStage().setScene(new Scene(root));
            Main.getStokGuncellemeStage().show();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }


    public void mevcutMalzemeClicked(MouseEvent mouseEvent) throws SQLException {

        ArrayList<StockItem> items =db.getAllStockItems();

        StringBuilder s=new StringBuilder();
        s.append("\tMALZEMELER\t\t\t\tMİKTARI\n");
        for(StockItem i:items){

            s.append("\t" + i.getInfo());
            s.append("\n");
        }

        mevcutMalzemeText.setText(s.toString());

    }
}
