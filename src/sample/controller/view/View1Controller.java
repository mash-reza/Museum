package sample.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectArtistView;

import java.sql.SQLException;


public class View1Controller {

    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artObjectTitle;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artObjectYear;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artObjectDescription;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artObjectCountry;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artistName;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artistBorn;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artistDied;
    @FXML
    public TableColumn<String, ArtObjectArtistView> artistEpoch;

    @FXML
    public void initialize() {
        artObjectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        artObjectYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        artObjectDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        artObjectCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        artistName.setCellValueFactory(new PropertyValueFactory<>("name"));
        artistBorn.setCellValueFactory(new PropertyValueFactory<>("dateBorn"));
        artistDied.setCellValueFactory(new PropertyValueFactory<>("dateDied"));
        artistEpoch.setCellValueFactory(new PropertyValueFactory<>("epoch"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().view1());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
