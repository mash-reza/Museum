package sample.controller.stored_procedure;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.pojo.ArtObject;

import java.sql.SQLException;
import java.util.Date;

public class Store3Controller {

    @FXML
    public TableView tableView;

    @FXML
    public TableColumn<String, ArtObject> title;
    public TableColumn<Date, ArtObject> year;
    public TableColumn<String, ArtObject> description;
    public TableColumn<String, ArtObject> country;
    public TableColumn<String, ArtObject> epoch;
    public TableColumn<String, ArtObject> style;
    public TableColumn<String, ArtObject> category;
    public TableColumn<String, ArtObject> type;

    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        epoch.setCellValueFactory(new PropertyValueFactory<>("epoch"));
        style.setCellValueFactory(new PropertyValueFactory<>("style"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().storeProcedure3());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
