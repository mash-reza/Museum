package sample.controller.stored_procedure;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseUtil;
import sample.model.pojo.Exhibition;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class Store2Controller {

    @FXML
    public TableView tableView;

    @FXML
    public TableColumn<String, Exhibition> title;
    @FXML
    public TableColumn<Date, Exhibition> start;
    @FXML
    public TableColumn<Date, Exhibition> end;

    @FXML
    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));

        try {
            tableView.getItems().addAll(DatabaseUtil.getObject().storeProcedure2());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
