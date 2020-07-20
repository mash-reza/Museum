package sample.controller.function;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.database.DatabaseUtil;

import java.sql.SQLException;


public class ScalarValued1 {
    @FXML
    public Label label;
    @FXML
    public TextField textField;
    @FXML
    public Button button;

    @FXML
    public void initialize() {
        button.setOnMousePressed(event -> {
            String age = null;
            try {
                age = DatabaseUtil.getObject().scalarFunction1(textField.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (age != null)
                label.setText(textField.getText() + " is " + age + " years old.");
            else
                label.setText("No artist found with this name!");
            label.setVisible(true);
        });
    }
}
