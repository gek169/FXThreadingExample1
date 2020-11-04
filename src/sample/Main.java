package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        Label lbl = new Label();
        Runnable r1 = new Runnable(){
            public void run() {
                for(int i = 0; i < 100; i++)
                {
                    System.out.println("Hello from the thread!");
                    try{sleep(200);}catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r1);
        t1.start();
        btn.setText("Say 'Hello World'");
        lbl.setText("Bruh moment");
        lbl.setTranslateY(40);
        lbl.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("You clicked me!");
                    }
                }
        );
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(lbl);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}