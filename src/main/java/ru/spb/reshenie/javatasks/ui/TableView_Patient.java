package ru.spb.reshenie.javatasks.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ru.spb.reshenie.javatasks.entity.Patient;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;

public class TableView_Patient extends TableView<Patient> {
    public TableView_Patient(ObservableList<Patient> ol) throws ClassNotFoundException {
        super(ol);
        this.setMinSize(700, 700);
        this.getColumns().addAll(getColumns(ol));
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private LinkedList<TableColumn<Patient, Object>> getColumns(ObservableList<Patient> ol)
            throws ClassNotFoundException {
        Field[] fields = Class.forName(ol.get(0).getClass().getName()).getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        LinkedList<TableColumn<Patient, Object>> tCols = new LinkedList<>();
        for (Field field : fields) {
            tCols.add(getColumnFromField(field));
        }
        return tCols;
    }

    private TableColumn<Patient, Object> getColumnFromField(Field field) {
        TableColumn<Patient, Object> column = new TableColumn<>(field.getName());
        column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Patient, Object> call(TableColumn<Patient, Object> soCalledFriendStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toString());
                            if (item.toString().equals("МУЖ"))
                                this.getTableRow().setStyle("-fx-background-color: #7c71ff");
                            if (item.toString().equals("ЖЕН"))
                                this.getTableRow().setStyle("-fx-background-color: #ee8fc1");
                        }
                    }
                };
            }
        });
        return column;
    }
}

//public class TableView_Patient extends TableView<Patient> {
//    public TableView_Patient(ObservableList<Patient> ol) throws ClassNotFoundException {
//        super(ol);
//        this.setMinSize(700, 700);
//        this.getColumns().addAll(getColumns(ol));
//        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//    }
//
//    private LinkedList<TableColumn<Patient, Object>> getColumns(ObservableList<Patient> ol)
//            throws ClassNotFoundException {
//        Field[] fields = Class.forName(ol.get(0).getClass().getName()).getDeclaredFields();
//        LinkedList<TableColumn<Patient, Object>> tCols = new LinkedList<>();
//        for (Field field : fields) {
//            tCols.add(getColumnFromField(field));
//        }
//        return tCols;
//    }
//
//    private TableColumn<Patient, Object> getColumnFromField(Field field) {
//        String s = field.getName();
//        TableColumn<Patient, Object> f = new TableColumn<>(s);
//        f.setCellValueFactory(new PropertyValueFactory<>(s));
//        return f;
//    }


// НЕ ТРОГАТЬ