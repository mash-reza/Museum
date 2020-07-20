package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import sample.database.DatabaseUtil;

import java.io.File;
import java.util.prefs.Preferences;

public class BackupController {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button button;
    @FXML
    public Button select;
    @FXML
    public TextField textField;
    @FXML
    public Button autoButton;
    @FXML
    public DatePicker datePicker;
    @FXML
    public RadioButton daily;
    @FXML
    public RadioButton weekly;
    @FXML
    public RadioButton monthly;

    private Preferences preferences;
    private String IS_BACKED_UP_KEY = "prefkey";


    @FXML
    public void initialize() {
        preferences = Preferences.userRoot().node(getClass().getName());
        if (preferences.getBoolean(IS_BACKED_UP_KEY, false)) {
            autoButton.setText("UNSET AUTOMATIC BACKUP");
        } else {
            autoButton.setText("SET AUTOMATIC BACKUP");
        }

        button.setOnMousePressed(event -> {
            if (!textField.getText().isEmpty()) {

                boolean result = DatabaseUtil.getObject().backup(textField.getText());
                if (result) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Database successfully backed up to disk",
                            ButtonType.CLOSE);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Backup Failed!",
                            ButtonType.CANCEL);
                    alert.show();
                }

            } else {
                DirectoryChooser chooser = new DirectoryChooser();
                File file = chooser.showDialog(anchorPane.getScene().getWindow());
                if (file != null) {
                    textField.setText(file.getAbsolutePath());
                }
            }
        });
        select.setOnMousePressed(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File file = chooser.showDialog(anchorPane.getScene().getWindow());
            if (file != null) {
                textField.setText(file.getAbsolutePath());
            }
        });
        autoButton.setOnMousePressed(event -> {
            StringBuilder sb = null;
            if (datePicker.getValue() != null) {
                sb = new StringBuilder(datePicker.getValue().toString());
                sb.delete(4, 5);
                sb.delete(6, 7);
                System.out.println(sb);
            }

            int frequency;
            if (daily.isSelected()) {
                frequency = 4;
            } else if (weekly.isSelected()) {
                frequency = 8;
            } else {
                frequency = 16;
            }

            if (preferences.getBoolean(IS_BACKED_UP_KEY, false)) {
                System.out.println("disable auto backup: " + DatabaseUtil.getObject().disableAutoBackup());
                preferences.putBoolean(IS_BACKED_UP_KEY, false);
                autoButton.setText("SET AUTOMATIC BACKUP");
            } else if (datePicker.getValue() != null) {
                System.out.println("enable auto backup: " + DatabaseUtil.getObject().enableAutoBackup(frequency, sb.toString()));
                preferences.putBoolean(IS_BACKED_UP_KEY, true);
                autoButton.setText("UNSET AUTOMATIC BACKUP");
            }
        });

        daily.setOnMouseClicked(event -> {
            daily.setSelected(true);
            weekly.setSelected(false);
            monthly.setSelected(false);
        });
        weekly.setOnMouseClicked(event -> {
            weekly.setSelected(true);
            daily.setSelected(false);
            monthly.setSelected(false);
        });
        monthly.setOnMouseClicked(event -> {
            monthly.setSelected(true);
            daily.setSelected(false);
            weekly.setSelected(false);
        });
    }

}
