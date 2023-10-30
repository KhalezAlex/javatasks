package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class Pane_Controls extends HBox {
    public Pane_Controls() {
        super(new TextArea(), new Button("Поиск"), new Button("Очистить"));
        this.setStyle("-fx-padding: 30px;");
        lookup("TextArea").setId("TextArea");
        lookup("TextArea").setStyle("-fx-pref-height: 40px;");
        lookupAll("Button").stream().toList().get(0).setId("search_btn");
        lookupAll("Button").stream().toList().get(1).setId("clear_btn");
    }
}
