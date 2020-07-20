package sample.controller.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectPainting;

import java.sql.SQLException;


public class View3Controller {
    @FXML
    public TableView tableView;
    @FXML
    public TableColumn<String, ArtObjectPainting> artObjectTitle;
    @FXML
    public TableColumn<String, ArtObjectPainting> style;
    @FXML
    public TableColumn<String, ArtObjectPainting> paintType;

    @FXML
    public void initialize(){
        artObjectTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        style.setCellValueFactory(new PropertyValueFactory<>("style"));
        paintType.setCellValueFactory(new PropertyValueFactory<>("paintType"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().view3());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
