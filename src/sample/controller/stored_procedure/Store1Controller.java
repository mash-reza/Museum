package sample.controller.stored_procedure;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.database.DatabaseUtil;

import java.sql.SQLException;


public class Store1Controller {

    @FXML
    public TextField moneyTextField;
    @FXML
    public Button calculateButton;

    @FXML
    public void initialize() {
        calculateButton.setOnMousePressed(event ->
        {
            try {
                moneyTextField.setText(DatabaseUtil.getObject().storeProcedure1());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
