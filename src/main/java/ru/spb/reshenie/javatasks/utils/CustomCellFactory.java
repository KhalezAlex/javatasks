package ru.spb.reshenie.javatasks.utils;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;
import ru.spb.reshenie.javatasks.entity.Patient;


public class CustomCellFactory implements Callback<TableColumn<Patient, Object>, TableCell<Patient, Object>> {
    @Override
    public TableCell<Patient, Object> call(TableColumn<Patient, Object> col) {
        return new TableCell<>() {
            @Override
            public void updateItem(Object field, boolean empty) {
                super.updateItem(field, empty);
                this.setStyle("-fx-alignment: center");
                if (field != null) {
                    colorRowsBySex(this, field);
                    customizeFioCell(this, field);
                }
            }
        };
    }

    private void customizeFioCell(TableCell<Patient, Object> cell, Object field) {
        String[] tmp = field.toString().split(" ");
        // костыль. выяснить, можно ли по ячейке достучаться до названия столбца
        if (tmp.length == 3 && !tmp[1].equals("-") &&
                cell.getTableRow() != null && cell.getTableRow().getItem() != null) {
            cell.setTooltip(new Tooltip(cell.getTableRow().getItem().getFio()));
            cell.setStyle("-fx-font-weight: 700");
        }
    }

    private void colorRowsBySex(TableCell<Patient, Object> cell, Object field) {
        cell.setText(field.toString());
        if (cell.getTableRow() != null) {
            if (field.toString().equals("МУЖ")) {
                cell.getTableRow().setStyle("-fx-background-color: #7c71ff");
            }
            if (field.toString().equals("ЖЕН")) {
                cell.getTableRow().setStyle("-fx-background-color: #ee8fc1");
            }
        }
    }
}
