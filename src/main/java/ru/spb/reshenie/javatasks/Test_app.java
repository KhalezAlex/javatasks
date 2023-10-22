package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.db.AgentDB;

import java.io.IOException;

public class Test_app extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Test_app.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        AgentDB agentDB = new AgentDB();
        System.out.println(agentDB.getPersonsByString("нна"));
    }

    public static void main(String[] args) {
        launch();
    }
}