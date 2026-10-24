package com.example.crapsgame262;

import com.example.crapsgame262.views.WelcomeView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*
        VBox root = new VBox();
        Label lblHello = new Label("Hello");
        lblHello.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello from Label!");
                System.out.println(event.getSource());
                System.out.println(event.getTarget());
                System.out.println(event.getEventType());
            }
        });
        root.getChildren().add(lblHello);

        Button btnHello = new Button("Clic");
        btnHello.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hello!");
                System.out.println(event.getSource());
                System.out.println(event.getTarget());
                System.out.println(event.getEventType());
            }
        });
        root.getChildren().add(btnHello);

        Scene scene = new Scene(root, 200, 200);

         */

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("welcome-view.fxml")
        );
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        */

        WelcomeView welcomeView = WelcomeView.getInstance();
        welcomeView.show();

    }
}
