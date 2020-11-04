package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    Runnable r1;
    Button btn;
    Label lbl;
    Canvas canny;
    GraphicsContext gc;
    int shouldKillThread = 0;
    String LabelText = "<No Data>";
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ooooo");
        btn = new Button();
        lbl = new Label();
        canny = new Canvas(640,480);
        gc = canny.getGraphicsContext2D();
        r1 = new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++){
                    try{sleep(200);}catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                    LabelText = "Counter = " + i;
                    if(shouldKillThread > 0) {
                        LabelText = "Thread Died at " + i;
                        return;
                    }
                }
                LabelText = "You were so patient the thread actually completed.";
            }
        };
        Thread t1 = new Thread(r1);

        btn.setText("Kill The Thread.");
        lbl.setText(LabelText);
        lbl.setTranslateY(40);
        lbl.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("You clicked me!");
                        lbl.setText(LabelText);
                    }
                }
        );
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Murdering Thread...");
                shouldKillThread = 1;
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(canny);
        root.getChildren().add(btn);
        root.getChildren().add(lbl);


        primaryStage.setScene(new Scene(root, 640, 480, Color.BLACK));
        primaryStage.show();
        t1.start();
        //Platform.runLater(r1);
    }
}