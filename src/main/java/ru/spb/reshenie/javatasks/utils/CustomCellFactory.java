package ru.spb.reshenie.javatasks.utils;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import ru.spb.reshenie.javatasks.entity.Patient;

import java.util.Objects;


public class CustomCellFactory implements Callback<TableColumn<Patient, Object>, TableCell<Patient, Object>> {
    @Override
    public TableCell<Patient, Object> call(TableColumn<Patient, Object> col) {
        return new TableCell<>() {
            @Override
            public void updateItem(Object field, boolean empty) {
                super.updateItem(field, empty);
                this.setStyle("-fx-alignment: center");
                if (field != null) {
                    customizePropertyCells(this, field);
                }
            }
        };
    }

    private void customizePropertyCells(TableCell<Patient, Object> cell, Object field) {
        colorRowsBySex(cell, field);
        customizeFioCell(cell, field);
        customizeFinSourceCell(cell, field);
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

    private void customizeFinSourceCell(TableCell<Patient, Object> cell, Object field) {
        if (field.toString().equals("1")) {
            cell.setTooltip(new Tooltip("ОМС"));
//            setCellGraphics(cell, "/img/oms.png");
        }
        if (field.toString().equals("2")) {
            cell.setTooltip(new Tooltip("ДМС"));
//            setCellGraphics(cell, "/img/dms.png");
        }
        if (field.toString().equals("3")) {
            cell.setTooltip(new Tooltip("Наличный расчет"));
//            setCellGraphics(cell, "/img/cash.png");
        }
    }

    private void setCellGraphics(TableCell<Patient, Object> cell, String imgPath) {
        HBox graphicContainer = new HBox();
        graphicContainer.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        iv.setFitHeight(25);
        iv.setPreserveRatio(true);
        graphicContainer.getChildren().add(iv);
        cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphicContainer));
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
