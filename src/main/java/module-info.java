module com.example.crapsgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.crapsgame to javafx.fxml;
    exports com.example.crapsgame;
}