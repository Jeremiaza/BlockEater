package view;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Interface UI = new Interface();
        TextField howmany = new TextField();
        Text text = new Text();
        text.setText("How many?");
        Button button = new Button();
        button.setText("Nom nom!");
        VBox hb = new VBox();
        hb.getChildren().addAll(text, howmany, button);
        hb.setSpacing(10);
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(125, 100);
        root.getChildren().add(canvas);
        root.getChildren().add(hb);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int k = parseInt(howmany.getText());
                UI.setK(k);
                UI.start(new Stage());
            }
        });
        primaryStage.show();
    }
}
