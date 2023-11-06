package ru.spb.reshenie.javatasks.utils;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
                this.getStyleClass().add("cell");
                if (field != null) {
                    setCell(this, field);
                }
            }
        };
    }

    private void setCell(TableCell<Patient, Object> cell, Object field) {
        String str = field.toString();
        if (!str.equals("1") && !str.equals("2") && !str.equals("3")) {
            cell.setText(field.toString());
        }
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
            cell.getStyleClass().add("cell-fio");
        }
    }

    private void customizeFinSourceCell(TableCell<Patient, Object> cell, Object field) {
        if (field.toString().equals("1")) {
            setFinSourceCellGraphics(cell, "oms.png", "ОМС");
        }
        if (field.toString().equals("2")) {
            setFinSourceCellGraphics(cell, "dms.png", "ДМС");
        }
        if (field.toString().equals("3")) {
            setFinSourceCellGraphics(cell, "cash.png", "Наличный расчет");
        }
    }

    private void setFinSourceCellGraphics(TableCell<Patient, Object> cell, String img, String tooltip) {
        HBox graphicContainer = new HBox();
        graphicContainer.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/img/".concat(img)))));
        iv.setFitHeight(25);
        iv.setPreserveRatio(true);
        graphicContainer.getChildren().add(iv);
        cell.setTooltip(new Tooltip(tooltip));
        cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphicContainer));
    }


    private void colorRowsBySex(TableCell<Patient, Object> cell, Object field) {
        if (cell.getTableRow() != null) {
            if (field.toString().equals("МУЖ")) {
                setStyleClass(cell.getTableRow(), "row-man");
//                cell.getTableRow().getStyleClass().add("row-man");
            }
            if (field.toString().equals("ЖЕН")) {
                setStyleClass(cell.getTableRow(), "row-woman");
//                cell.getTableRow().getStyleClass().add("row-woman");
            }
        }
    }

    private void setStyleClass(TableRow<Patient> row, String styleClass) {
        row.getStyleClass().remove("row-man");
        row.getStyleClass().remove("row-woman");
        row.getStyleClass().add(styleClass);
    }
}
