package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.database.DatabaseUtil;

public class UserController {
    @FXML
    public TextField textField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public Button addButton;
    @FXML
    public CheckBox checkPolicy;
    @FXML
    public CheckBox checkExpiration;
    @FXML
    public Label resultLabel;


    @FXML
    public void initialize(){
        addButton.setOnMousePressed(event -> {
            if(!textField.getText().isEmpty() && !passwordTextField.getText().isEmpty()){
                if(DatabaseUtil.getObject().addUser(textField.getText(),passwordTextField.getText(),checkPolicy.isSelected(),checkExpiration.isSelected())){
                    resultLabel.setText("User added successfully to database.");
                    resultLabel.setVisible(true);
                }else {
                    resultLabel.setText("User add failed!");
                    resultLabel.setVisible(true);
                }
            }
        });
    }
}
