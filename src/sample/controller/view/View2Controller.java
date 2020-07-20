package sample.controller.view;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectBorrowed;


import java.sql.SQLException;
import java.util.Date;

public class View2Controller {

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObjectBorrowed> artObjectTitle;
    @FXML
    public TableColumn<Date,ArtObjectBorrowed> borrowedDateBorrowed;
    @FXML
    public TableColumn<Date,ArtObjectBorrowed> borrowedDateToReturn;

    @FXML
    public void initialize(){

        artObjectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowedDateBorrowed.setCellValueFactory(new PropertyValueFactory<>("dateBorrowed"));
        borrowedDateToReturn.setCellValueFactory(new PropertyValueFactory<>("dateToReturn"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().view2());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
