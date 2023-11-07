package ru.spb.reshenie.javatasks.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.spb.reshenie.javatasks.interfaces.DAO;
import ru.spb.reshenie.javatasks.db.DbDao;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.utils.CustomCellFactory;

import java.lang.reflect.Field;
import java.util.*;

public class TableViewPatient extends TableView<Patient> {
    public TableViewPatient(String className) throws ClassNotFoundException {
        super();
        this.setMinSize(800, 700);
        this.getColumns().addAll(getColumnNames(className));
        this.setItems(getOl());
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private List<TableColumn<Patient, Object>> getColumnNames(String className)
            throws ClassNotFoundException {
        Map<String, String> columnNames = columnNames();
        return new ArrayList<>(Arrays.
                stream(Class.forName(className).getDeclaredFields())
                .map(Field::getName)
                .filter(fn -> !fn.equals("fio"))
                .map(fn -> getColumnFromField(fn, columnNames.get(fn)))
                .toList());
    }

    private TableColumn<Patient, Object> getColumnFromField(String prop, String colName) {
        TableColumn<Patient, Object> column = new TableColumn<>(colName);
        column.setCellValueFactory(new PropertyValueFactory<>(prop));
        column.setCellFactory(new CustomCellFactory());
        return column;
    }

    private Map<String, String> columnNames() {
        Map<String, String> columnNames = new HashMap<>();
        columnNames.put("num", "Номер");
        columnNames.put("snils", "СНИЛС");
        columnNames.put("sex", "Пол");
        columnNames.put("fioAbbr", "ФИО");
        columnNames.put("birthDate", "Дата рожд.");
        columnNames.put("age", "Возраст");
        columnNames.put("policy", "Полис");
        columnNames.put("finSource", "Ист.Фин.");
        return columnNames;
    }

    private ObservableList<Patient> getOl() {
        DAO dao = new DbDao();
        ObservableList<Patient> temp = dao.getAll();
        dao.closeConnection();
        return temp;
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


// НЕ ТРОГАТЬ - потом разобрать