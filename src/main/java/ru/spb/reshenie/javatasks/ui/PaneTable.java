package ru.spb.reshenie.javatasks.ui;

import javafx.scene.layout.Pane;
import ru.spb.reshenie.javatasks.entity.Patient;

public class PaneTable extends Pane {
    public PaneTable() throws ClassNotFoundException {
        super(new TableViewPatient(Patient.class.getName()));
    }
}
