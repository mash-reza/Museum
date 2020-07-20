package sample.controller.stored_procedure;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.dto.ArtObjectPainting;
import sample.model.dto.ArtObjectSculpture;

import java.sql.SQLException;


public class Store4Controller {
    @FXML
    public TableView tableView;

    @FXML
    public TableColumn title;
    @FXML
    public TableColumn description;
    @FXML
    public TableColumn year;
    @FXML
    public TableColumn country;
    @FXML
    public TableColumn epoch;
    @FXML
    public TableColumn style;
    @FXML
    public TableColumn status;
    @FXML
    public TableColumn category;
    @FXML
    public TableColumn height;
    @FXML
    public TableColumn weight;
    @FXML
    public TableColumn sculptureType;

    @FXML
    public RadioButton paintingRadio;
    @FXML
    public RadioButton sculptureRadio;

    @FXML
    public Button button;

    @FXML
    public void initialize() {
        paintingRadio.setOnMouseClicked(event -> {
            sculptureRadio.setSelected(false);
            paintingRadio.setSelected(true);
        });
        sculptureRadio.setOnMouseClicked(event -> {
            paintingRadio.setSelected(false);
            sculptureRadio.setSelected(true);
        });

        button.setOnMousePressed(event -> {
            if (sculptureRadio.isSelected())
                getSculpture();
            else if (paintingRadio.isSelected())
                getPaintings();
        });
    }

    private void getPaintings() {
        title.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("title"));
        description.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("description"));
        year.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("year"));
        country.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("country"));
        category.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("category"));
        epoch.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("epoch"));
        style.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("style"));
        status.setCellValueFactory(new PropertyValueFactory<String, ArtObjectPainting>("status"));
        height.setCellValueFactory(new PropertyValueFactory<String,ArtObjectPainting>("paintType"));
        weight.setCellValueFactory(new PropertyValueFactory<String,ArtObjectPainting>("material"));
        height.setText("Paint Type");
        weight.setText("Material");
        sculptureType.setVisible(false);

        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(DatabaseUtil.getObject().storeProcedure4("painting"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getSculpture() {
        title.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("title"));
        description.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("description"));
        year.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("year"));
        country.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("country"));
        category.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("category"));
        epoch.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("epoch"));
        style.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("style"));
        status.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("status"));
        height.setCellValueFactory(new PropertyValueFactory<Float,ArtObjectSculpture>("height"));
        weight.setCellValueFactory(new PropertyValueFactory<Float,ArtObjectSculpture>("weight"));
        sculptureType.setCellValueFactory(new PropertyValueFactory<String, ArtObjectSculpture>("sculptureType"));
        height.setText("Height");
        weight.setText("Weight");
        sculptureType.setText("Sculpture Type");
        sculptureType.setVisible(true);

        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(DatabaseUtil.getObject().storeProcedure4("sculpture"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
