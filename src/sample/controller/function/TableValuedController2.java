package sample.controller.function;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.pojo.ArtObject;

import java.sql.SQLException;
import java.util.Date;

public class TableValuedController2 {

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObject> name;
    @FXML
    public TableColumn<Date, ArtObject> type;
    @FXML
    public TableColumn<String, ArtObject> description;
    @FXML
    public TableColumn<String, ArtObject> address;
    @FXML
    public TableColumn<String, ArtObject> contact;
    @FXML
    public TableColumn<String, ArtObject> phone;
    @FXML
    public Button button;
    @FXML
    public TextField textField;

    @FXML
    public void initialize() {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        button.setOnMousePressed(event -> {
            tableView.getItems().clear();
            try {
                tableView.getItems().addAll(DatabaseUtil.getObject().tableFunction2(textField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
