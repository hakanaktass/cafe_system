package Proje.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

import Proje.Main;


public class menuController {
    
    @FXML
    private Button siparis = null;


    public void siparisClicked(MouseEvent mouseEvent) {
        openSiparisStage();
    }
    public void openSiparisStage(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proje/resources/siparis.fxml"));
            Parent root = loader.load();

            Main.getSiparisStage().setScene(new Scene(root));
            Main.getSiparisStage().show();
        }
        catch(IOException ex){
            ex.printStackTrace();
            //System.out.println(ex);
        }
    }

    @FXML
    private Button finans = null;

    public void finansClicked(MouseEvent mouseEvent) {openFinansStage();}

    public void openFinansStage(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proje/resources/finans.fxml"));
            Parent root = loader.load();

            Main.getFinansStage().setScene(new Scene(root));
            Main.getFinansStage().show();

        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private Button stok = null;

    public void stokClicked(MouseEvent mouseevent) {openStokStage();}

    public void openStokStage(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Proje/resources/stok.fxml"));
            Parent root = loader.load();

            Main.getStokStage().setScene(new Scene(root));
            Main.getStokStage().show();
        }
        catch(IOException ex){

            System.out.println(ex);
        }

    }

}