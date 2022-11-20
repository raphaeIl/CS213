package client;

import core.Flavor;
import core.Size;
import core.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import managers.StoreManager;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        stage.setTitle("RU Pizzeria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

//        StoreManager.getInstance().selectPizza(Style.Chicago, Flavor.Deluxe, Size.LARGE);
//        StoreManager.getInstance().selectPizza(Style.Chicago, Flavor.Meatzza, Size.SMALL);
//        StoreManager.getInstance().selectPizza(Style.NY, Flavor.Deluxe, Size.MEDIUM);
//        StoreManager.getInstance().selectPizza(Style.NY, Flavor.Meatzza, Size.LARGE);
//
//        StoreManager.getInstance().placeOrder();
//
//        StoreManager.getInstance().selectPizza(Style.Chicago, Flavor.BuildYourOwn, Size.MEDIUM);
//
//        StoreManager.getInstance().placeOrder();
//
//        System.out.println(StoreManager.getInstance().getOrderHistory());
    }


}