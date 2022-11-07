module com.example.fitnesscompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens javafx to javafx.fxml;
    exports javafx;
}