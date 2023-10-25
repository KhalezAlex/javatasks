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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        LinkedList<TableColumn<Patient, Object>> tCols = new LinkedList<>();
        Map<String, String> columnNames = columnNames();
        for (Field field : fields) {
            tCols.add(getColumnFromField(field.getName(), columnNames.get(field.getName())));
        }
        return tCols;
    }

    private TableColumn<Patient, Object> getColumnFromField(String prop, String colName) {
        TableColumn<Patient, Object> column = new TableColumn<>(colName);
        column.setCellValueFactory(new PropertyValueFactory<>(prop));
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Patient, Object> call(TableColumn<Patient, Object> soCalledFriendStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.toString());
                            if (item.toString().equals("МУЖ")) {
                                this.getTableRow().setStyle("-fx-background-color: #7c71ff");
                            }
                            if (item.toString().equals("ЖЕН")) {
                                this.getTableRow().setStyle("-fx-background-color: #ee8fc1");
                            }
                        }
                    }
                };
            }
        });
        return column;
    }

    private Map<String, String> columnNames() {
        Map<String, String> columnNames = new HashMap<>();
        columnNames.put("num", "Номер");
        columnNames.put("snils", "СНИЛС");
        columnNames.put("sex", "Пол");
        columnNames.put("fio", "ФИО");
        columnNames.put("birthDate", "Дата рожд.");
        columnNames.put("age", "Возраст");
        columnNames.put("policy", "Полис");
        columnNames.put("finSource", "Ист.Фин.");
        return columnNames;
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