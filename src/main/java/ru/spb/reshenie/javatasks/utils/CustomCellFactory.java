package ru.spb.reshenie.javatasks.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ru.spb.reshenie.javatasks.entity.Patient;

public class CustomCellFactory implements Callback<TableColumn<Patient, Object>, TableCell<Patient, Object>> {
    @Override
    public TableCell<Patient, Object> call(TableColumn<Patient, Object> col) {
        return new TableCell<>() {
            @Override
            public void updateItem(Object field, boolean empty) {
                super.updateItem(field, empty);
                colorRowsBySex(field, this);
            }
        };
    }

    private void colorRowsBySex(Object field, TableCell<Patient, Object> cell){
        if (field != null) {
            cell.setText(field.toString());
            if (field.toString().equals("МУЖ")) {
                cell.getTableRow().setStyle("-fx-background-color: #7c71ff");
            }
            if (field.toString().equals("ЖЕН")) {
                cell.getTableRow().setStyle("-fx-background-color: #ee8fc1");
            }
        }
    }
}
