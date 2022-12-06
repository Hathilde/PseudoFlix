package com.example.wearefucked;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import static javafx.application.Application.launch;

public class NetflixBeta extends Application {

    BorderPane root;
    StackPane stackPane;
    FlowPane flowPane;

    ScrollPane scrollPane;

    public void start(Stage stage) throws IOException {
        //Layers
        root = new BorderPane();
        stackPane = new StackPane();
        flowPane = new FlowPane();
        scrollPane = new ScrollPane(flowPane);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        stackPane.getChildren().add(scrollPane);

        //Styling
        flowPane.setPrefWrapLength(1200);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        //flowPane.setAlignment(Pos.CENTER);
        flowPane.setStyle("-fx-background-color: black");

        //Stackpane Styling
        stackPane.setAlignment(Pos.CENTER);
        flowPane.setCenterShape(true);

        //Scene
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setScene(scene1);
        stage.show();

        Scene scene = new Scene(stackPane, 800, 500);
        stage.setTitle("PseudoFlix");
        stage.setScene(scene);
        stage.show();

        makeMenuBar();              //Kald til makeMenuBar-metoden





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

        




       // stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    public void makeMenuBar(){
        MenuBar menuBar = new MenuBar();
        Menu Favoritter = new Menu("Favoritter");
        menuBar.getMenus().add(Favoritter);
        VBox vBox = new VBox(menuBar);
        //Scene scene = new Scene(vBox,root);
        root.setTop(menuBar);
    }

}
