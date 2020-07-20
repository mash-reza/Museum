package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.database.DatabaseUtil;

import javax.xml.crypto.Data;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent view1 = FXMLLoader.load(getClass().getResource("view/view/view1.fxml"));
        Parent view2 = FXMLLoader.load(getClass().getResource("view/view/view2.fxml"));
        Parent view3 = FXMLLoader.load(getClass().getResource("view/view/view3.fxml"));
        Parent view4 = FXMLLoader.load(getClass().getResource("view/view/view4.fxml"));
        Parent view5 = FXMLLoader.load(getClass().getResource("view/view/view5.fxml"));
        Parent store1 = FXMLLoader.load(getClass().getResource("view/stored_procedure/storeprocedure1.fxml"));
        Parent store2 = FXMLLoader.load(getClass().getResource("view/stored_procedure/storeprocedure2.fxml"));
        Parent store3 = FXMLLoader.load(getClass().getResource("view/stored_procedure/storeprocedure3.fxml"));
        Parent store4 = FXMLLoader.load(getClass().getResource("view/stored_procedure/storeprocedure4.fxml"));
        Parent tableFunction1 = FXMLLoader.load(getClass().getResource("view/function/tablevalued1.fxml"));
        Parent tableFunction2 = FXMLLoader.load(getClass().getResource("view/function/tablevalued2.fxml"));
        Parent scalarFunction1 = FXMLLoader.load(getClass().getResource("view/function/scalarvalued1.fxml"));
        Parent scalarFunction2 = FXMLLoader.load(getClass().getResource("view/function/scalarvalued2.fxml"));
        Parent backup = FXMLLoader.load(getClass().getResource("view/backup.fxml"));
        Parent user = FXMLLoader.load(getClass().getResource("view/user.fxml"));

        Menu viewMenu = new Menu("View");
        MenuItem view1menuItem = new MenuItem("getAllArtObjectAndArtistView");
        MenuItem view2menuItem = new MenuItem("getAllBorrowedView");
        MenuItem view3menuItem = new MenuItem("getAllPaintingsView");
        MenuItem view4menuItem = new MenuItem("getAllPermanentView");
        MenuItem view5menuItem = new MenuItem("getAllScupltureView");
        viewMenu.getItems().addAll(view1menuItem, view2menuItem, view3menuItem, view4menuItem, view5menuItem);


        Menu storeProcedureMenu = new Menu("Store Procedure");
        MenuItem store1MenuItem = new MenuItem("calculateMoneySpent");
        MenuItem store2MenuItem = new MenuItem("getExhibitionByDateRange");
        MenuItem store3MenuItem = new MenuItem("getOnDisplayArtObjects");
        MenuItem store4MenuItem = new MenuItem("getSculpture");
        storeProcedureMenu.getItems().addAll(store1MenuItem, store2MenuItem, store3MenuItem, store4MenuItem);

        Menu functionMenu = new Menu("Function");
        Menu tabalValuedMenu = new Menu("Table Valued");
        Menu scalarValuedMenu = new Menu("Scalar Valued");
        functionMenu.getItems().addAll(tabalValuedMenu, scalarValuedMenu);
        MenuItem tableValued1MenuItem = new MenuItem("getArtObjectsByCountry");
        MenuItem tableValued2MenuItem = new MenuItem("getCollection");
        tabalValuedMenu.getItems().addAll(tableValued1MenuItem, tableValued2MenuItem);
        MenuItem scalarValued1MenuItem = new MenuItem("calculateArtistAge");
        MenuItem scalarValued2MenuItem = new MenuItem("calculateHowLongArtObjectWasBorrowed");
        scalarValuedMenu.getItems().addAll(scalarValued1MenuItem, scalarValued2MenuItem);

        Menu backupMenu = new Menu("Backup");
        MenuItem backupMenuItem = new MenuItem("Get backup from database");
        backupMenu.getItems().add(backupMenuItem);

        Menu userMenu = new Menu("User");
        MenuItem userMenuItem = new MenuItem("Create User");
        userMenu.getItems().addAll(userMenuItem);

        MenuBar menuBar = new MenuBar(viewMenu, storeProcedureMenu, functionMenu,backupMenu,userMenu);
        BorderPane borderPane = new BorderPane(view1, menuBar, null, null, null);

        view1menuItem.setOnAction(event -> borderPane.setCenter(view1));
        view2menuItem.setOnAction(event -> borderPane.setCenter(view2));
        view3menuItem.setOnAction(event -> borderPane.setCenter(view3));
        view4menuItem.setOnAction(event -> borderPane.setCenter(view4));
        view5menuItem.setOnAction(event -> borderPane.setCenter(view5));
        store1MenuItem.setOnAction(event -> borderPane.setCenter(store1));
        store2MenuItem.setOnAction(event -> borderPane.setCenter(store2));
        store3MenuItem.setOnAction(event -> borderPane.setCenter(store3));
        store4MenuItem.setOnAction(event -> borderPane.setCenter(store4));
        tableValued1MenuItem.setOnAction(event -> borderPane.setCenter(tableFunction1));
        tableValued2MenuItem.setOnAction(event -> borderPane.setCenter(tableFunction2));
        scalarValued1MenuItem.setOnAction(event -> borderPane.setCenter(scalarFunction1));
        scalarValued2MenuItem.setOnAction(event -> borderPane.setCenter(scalarFunction2));
        backupMenuItem.setOnAction(event -> borderPane.setCenter(backup));
        userMenuItem.setOnAction(event -> borderPane.setCenter(user));

        primaryStage.setTitle("Museum System");
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
