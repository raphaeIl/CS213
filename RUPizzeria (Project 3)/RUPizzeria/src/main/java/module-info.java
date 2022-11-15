module com.example.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;


    opens client to javafx.fxml;
    exports client;
}