package ru.spb.reshenie.javatasks.ui;

import javafx.scene.layout.Pane;

public class PaneTable extends Pane {
    public PaneTable() throws ClassNotFoundException {
        super(new TableViewPatient());
    }
}
