package sample.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectSculpture;

import java.sql.SQLException;
import java.util.Date;

public class View5Controller {

    @FXML
    public TableView tableView;

    @FXML
    public TableColumn<String, ArtObjectSculpture> title;
    public TableColumn<String, ArtObjectSculpture> description;
    public TableColumn<String, ArtObjectSculpture> country;
    public TableColumn<Float, ArtObjectSculpture> height;
    public TableColumn<Float, ArtObjectSculpture> weight;
    public TableColumn<Date, ArtObjectSculpture> year;
    public TableColumn<String, ArtObjectSculpture> sculptureType;

    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        sculptureType.setCellValueFactory(new PropertyValueFactory<>("type"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().view5());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
