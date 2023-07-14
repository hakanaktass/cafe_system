package Proje.controller;

import Proje.BudgetItem;
import Proje.DatabaseConnection;
import Proje.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class finansController {



    DatabaseConnection db=new DatabaseConnection();
    @FXML
    private Button butce = null;

    @FXML
    private Button maasOde = null;

    @FXML
    private Button calisanBilgileri = null;

    @FXML
    private TextArea butceText=null;
    @FXML
    private TextArea calisanText=null;

    @FXML
    public TextField toplamTextField=null;

    public void maasOdeClicked(MouseEvent mouseEvent) throws SQLException {

        db.paySalaries(LocalDate.now().toString());
        butceTextGuncelle();

    }

    public void butceMouseClicked(MouseEvent mouseEvent) throws SQLException {
        butceTextGuncelle();
    }

    public void calisanBilgileriClicked(MouseEvent mouseEvent) throws SQLException {

        ArrayList<Worker> workers=db.getAllWorkers();

        StringBuilder s= new StringBuilder();

        for(Worker w:workers){

            s.append(w.getInfo());
            s.append("\n");

        }

        calisanText.setText(s.toString());

    }

    public void butceTextGuncelle() throws SQLException {

        ArrayList<BudgetItem> items =db.getAllBudgetItems();

        StringBuilder s= new StringBuilder();

        for(BudgetItem i:items){

            s.append(i.getInfo());
            s.append("\n");

        }

        butceText.setText(s.toString());

        toplamTextField.setText(String.valueOf(db.getTotalBudget()));
    }

}
