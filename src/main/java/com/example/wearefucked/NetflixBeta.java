package com.example.wearefucked;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static javafx.application.Application.launch;

public class NetflixBeta extends Application {

    public void start(Stage stage) throws IOException {
        //Layers
        StackPane root = new StackPane();
        FlowPane flowPane = new FlowPane();
        ScrollPane scrollPane = new ScrollPane(flowPane);
        root.getChildren().add(scrollPane);

        //Styling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        flowPane.setPrefWrapLength(1200);

        //Files
        File directory = new File("src/Data/filmplakater/filmplakater");
        File [] files = directory.listFiles();
        Image[] fileName = new Image [files.length ];
        ImageView  [ ] images = new ImageView[files.length];

        for (int i = 0; i < files.length ; i++) {
            System.out.println(files[i].getName());
            fileName[i] = new Image(files[i].toURI().toString());
            images[i] = new ImageView(fileName[i]);
            String titleName = files[i].getAbsoluteFile().getName();


            images[i].setUserData(titleName);
            images[i].setId(i + "");
            images[i].setOnMouseClicked((evt) -> {
                Node targetPictureSource = (Node) evt.getSource();
                String targetImage = images[Integer.parseInt(targetPictureSource.getId())].getUserData().toString();
                System.out.println(evt.getSceneX());
            });


            flowPane.getChildren().add(images[i]);
        }

        //Scene
        Scene scene = new Scene(root);
        stage.setTitle("PseudoFlix");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
