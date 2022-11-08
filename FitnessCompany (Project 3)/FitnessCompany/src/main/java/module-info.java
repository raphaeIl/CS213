module com.example.fitnesscompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens client to javafx.fxml;
    exports client;
}