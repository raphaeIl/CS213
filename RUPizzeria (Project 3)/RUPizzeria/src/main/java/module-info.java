module com.example.rupizzeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens client to javafx.fxml;
    exports client;
}