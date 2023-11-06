package ru.spb.reshenie.javatasks.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.spb.reshenie.javatasks.db.DAO;
import ru.spb.reshenie.javatasks.db.DbDao;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.ui.PaneTable;
import ru.spb.reshenie.javatasks.ui.TableViewPatient;
import ru.spb.reshenie.javatasks.utils.QueryWizard;

public class MainController {
    @FXML
    public TextArea textAreaQuery;
    @FXML
    public Button buttonSearch;
    @FXML
    public Button buttonCancel;
    @FXML
    public PaneTable paneTable;

    public void buttonSearchClickHandler() {
        if (textAreaQuery.getText().equals(""))
            return;
        ObservableList<Patient> selection = getSelection(textAreaQuery.getText());
        updateTable(selection);
    }

    private ObservableList<Patient> getSelection(String queryStr) {
        QueryWizard qw = new QueryWizard(queryStr);
        DAO dao = new DbDao();
        ObservableList<Patient> temp = FXCollections.observableArrayList(dao.getByQuery(qw.getQuery()));
        dao.closeConnection();
        return temp;
    }

    private void updateTable(ObservableList<Patient> ol) {
        TableView<Patient> table = (TableViewPatient) paneTable.getChildren().get(0);
        while (table.getItems().size() > 0) {
            table.getItems().remove(0);
        }
        table.getItems().addAll(ol);
        table.refresh();
    }

    public void buttonCancelClickHandler() {
        DAO dao = new DbDao();
        updateTable(FXCollections.observableArrayList(dao.getAll()));
        dao.closeConnection();
        textAreaQuery.setText("");
    }

    public void textAreaQueryKeyPressedHandler(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onTextAreaEnterPressedHandler();
        }
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            buttonCancelClickHandler();
        }
    }

    private void onTextAreaEnterPressedHandler() {
        String temp = textAreaQuery.getText();
        temp = temp.replace("\n", " ").replace("\t", " ");
        textAreaQuery.setText(temp);
        buttonSearchClickHandler();
    }
}