package sample.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectPermanent;

import java.sql.SQLException;
import java.util.Date;

public class View4Controller {

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObjectPermanent> title;
    @FXML
    public TableColumn<String, ArtObjectPermanent> year;
    @FXML
    public TableColumn<Long, ArtObjectPermanent> cost;
    @FXML
    public TableColumn<Date, ArtObjectPermanent> dateAcquired;

    @FXML
    public void initialize() {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dateAcquired.setCellValueFactory(new PropertyValueFactory<>("dateAcquired"));
        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().view4());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
