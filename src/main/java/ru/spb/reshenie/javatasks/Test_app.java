package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.db.AgentDB;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.ui.Pane_Controls;
import ru.spb.reshenie.javatasks.ui.TableView_Patient;
import ru.spb.reshenie.javatasks.utils.QueryWizard;

import java.util.LinkedList;

public class Test_app extends Application {
    private ObservableList<Patient> ol;
    private VBox root;
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        AgentDB agentDB = new AgentDB();
        LinkedList<Patient> pts = agentDB.getAll();
        ol = FXCollections.observableArrayList(pts);
        TableView_Patient table = new TableView_Patient(ol);
        Pane_Controls controls = new Pane_Controls();
        root = new VBox(controls, table);
        root.setId("root");
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("java_tasks");
        stage.setScene(scene);
        stage.show();

        setButtonListeners();
        setTextAreaListeners();
    }

    private void setButtonListeners() {
        ((Button) root.lookup("#search_btn"))
                .setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        executeQuery();
                    }
                });

        ((Button) root.lookup("#clear_btn"))
                .setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        showAllBase();
                    }
                });
    }

    private void setTextAreaListeners() {
        (root.lookup("TextArea")).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String temp = ((TextArea) root.lookup("TextArea")).getText();
                    temp = temp.substring(0, temp.length() - 1);
                    ((TextArea)root.lookup(("TextArea"))).setText(temp);
                    executeQuery();
                }
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    showAllBase();
                }
            }
        });

    }

    private void executeQuery() {
        QueryWizard qw = new QueryWizard(((TextArea) (root.lookup("TextArea"))).getText(), ol);
        root.getChildren().remove(1);
        try {
            ObservableList<Patient> result = qw.collectQuery();
            if (result.size() > 0) {
                root.getChildren().add(new TableView_Patient(result));
            }
            else {
                root.getChildren().add(new Label("По таким критериям пациентов не найдено!"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllBase() {
        ((TextArea) root.lookup("TextArea")).setText("");
        root.getChildren().remove(1);
        try {
            root.getChildren().add(new TableView_Patient(ol));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}


//        int index = 0;
//        Set<Node> rows = table.lookupAll("TableRow");
//        System.out.println(rows.size());
//        for (Node n : rows) {
//            if (n instanceof TableRow) {
//                TableRow<Patient> row = (TableRow) n;
//                if (table.getItems().get(index).getSex() == 1)
//                    row.setStyle("-fx-background-color: #7c71ff");
//                else
//                    row.setStyle("-fx-background-color: #ee8fc1");
//                index++;
//                if (index == table.getItems().size())
//                    break;
//            }
//        }

//        table.setRowFactory(tv -> new TableRow<>() {
//            @Override
//            protected void updateItem(Patient patient, boolean empty) {
//                super.updateItem(patient, empty);
//                if (patient.getSex() == 1)
//                    setStyle("-fx-background-color: #7c71ff;");
//                else
//                    setStyle("-fx-background-color: #ee8fc1;");
//            }
//        });


//    private void setRowStyles(TableView_Patient table) {
//        Set<Node> rows = table.lookupAll("TableRow");
//        System.out.println("rows: " + rows.size());
//        System.out.println("table: " + table.getItems().size());
//        int i = 0;
//        for (Node n : rows) {
//            if (n instanceof TableRow<?>) {
//                TableRow<Patient> row = (TableRow<Patient>) n;
//                if (table.getItems().get(i).getSex() == 1) row.setStyle("-fx-background-color: red");
//                else row.setStyle("-fx-background-color: green");
//                i++;
//                if (i == table.getItems().size()) break;
//            }
//        }
//    }