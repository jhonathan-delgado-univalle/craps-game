module com.example.crapsgame262 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.crapsgame262 to javafx.fxml;
    exports com.example.crapsgame262;
    opens com.example.crapsgame262.controllers to javafx.fxml;
    exports com.example.crapsgame262.controllers;
}