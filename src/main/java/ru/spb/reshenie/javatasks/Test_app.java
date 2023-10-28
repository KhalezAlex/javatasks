package ru.spb.reshenie.javatasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.spb.reshenie.javatasks.db.AgentDB;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.ui.TableView_Patient;

import java.util.LinkedList;

public class Test_app extends Application {
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        AgentDB agentDB = new AgentDB();
        LinkedList<Patient> pts = agentDB.getAll();
        ObservableList<Patient> ol = FXCollections.observableArrayList(pts);
        TableView_Patient table = new TableView_Patient(ol);
        Pane root = new Pane(table);
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("java_tasks");
        stage.setScene(scene);
        stage.show();
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