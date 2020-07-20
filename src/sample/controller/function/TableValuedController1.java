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
import java.util.Collections;
import java.util.Date;

public class TableValuedController1 {

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObject> title;
    @FXML
    public TableColumn<Date, ArtObject> year;
    @FXML
    public TableColumn<String, ArtObject> description;
    @FXML
    public Button button;
    @FXML
    public TextField textField;

    @FXML
    public void initialize() {

        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        button.setOnMousePressed(event -> {
                tableView.getItems().clear();
            try {
                tableView.getItems().addAll(DatabaseUtil.getObject().tableFunction1(textField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
