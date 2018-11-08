
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class Interface extends Application {
    private int k = 0;
    public void setK(int k) {
        this.k = k;
    }
    private ArrayList<Food> cities = new ArrayList<>();
    public void start(Stage stage) {
        final int leveys = 800;
        final int korkeus = 800;
        stage.setTitle("Random Walking");
        Text textfieldxy = new Text();
        textfieldxy.setFill(Color.WHITE);
        HBox hb = new HBox();
        hb.getChildren().add(textfieldxy);
        hb.setSpacing(10);
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(leveys, korkeus);
        root.getChildren().add(canvas);
        root.getChildren().add(hb);

        GraphicsContext drawer = canvas.getGraphicsContext2D();

        Worm worm = new Worm(200, 200);
        drawer.setFill(Color.BLACK);
        drawer.fillRect(0, 0, leveys, korkeus);
        Random r = new Random();
        int a = 0;
        while (a<k) {
            cities.add(new Food(r.nextInt(worm.getWidth()), r.nextInt(worm.getHeight())));
            drawer.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            drawer.fillRect(cities.get(a).getX()*4,cities.get(a).getY()*4,4,4);
            a++;
        }
        new AnimationTimer() {

            private long sleepNanoseconds = 10 * 1000000;
            private long prevTime = 0;

            public void handle(long currentNanoTime) {

                if ((currentNanoTime - prevTime) < sleepNanoseconds) {
                    return;
                }

                HashMap<Integer, HashMap<Integer, Double>> coordinates = worm.getCoordinates();
                int x = 0;
                while (x < coordinates.size()) {

                    int y = 0;
                    while (y < coordinates.get(x).size()) {

                        if (coordinates.get(x).get(y) > 0) {

                            double vari = Math.min(1.0, coordinates.get(x).get(y));
                            Color color = new Color(vari, vari, vari, 1);
                            drawer.setFill(color);
                            drawer.fillRect(x * 4, y * 4, 4, 4);
                        }

                        y++;
                    }

                    x++;
                }

                for (int i = 0; i<cities.size(); i++) {
                    if (worm.getLocationX() == cities.get(i).getX() && worm.getLocationY() == cities.get(i).getY()) {
                        cities.remove(i);
                    }
                }
                textfieldxy.setText("x:" + worm.getLocationX() + " y:" + worm.getLocationY());
                try {
                    worm.walk(cities);
                } catch (IndexOutOfBoundsException e) {
                    textfieldxy.setFill(Color.GREEN);
                    textfieldxy.setText("You are win");
                }

                prevTime = currentNanoTime;
            }
        }.start();
        new AnimationTimer() {
            private long sleepNanoseconds = 250 * 1000000;
            private long prevTime = 0;
            @Override
            public void handle(long currentNanoTime) {

                if ((currentNanoTime - prevTime) < sleepNanoseconds) {
                    return;
                }
                prevTime = currentNanoTime;
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
