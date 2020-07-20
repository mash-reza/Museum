package sample.controller.function;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.database.DatabaseUtil;

import java.sql.SQLException;

public class ScalarValued2 {
    @FXML
    public Label label;
    @FXML
    public TextField textField;
    @FXML
    public Button button;

    @FXML
    public void initialize() {
        button.setOnMousePressed(event -> {
            String time = null;
            try {
                time = DatabaseUtil.getObject().scalarFunction2(textField.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (time != null)
                label.setText(textField.getText() + " is " + time + " years borrowed.");
            else
                label.setText("No art object found with this name or if it is found it is not in borrowed category!");
            label.setVisible(true);
        });
    }
}
