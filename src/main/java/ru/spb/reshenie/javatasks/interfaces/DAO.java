package ru.spb.reshenie.javatasks.interfaces;

import javafx.collections.ObservableList;
import ru.spb.reshenie.javatasks.entity.Patient;

public interface DAO {
    ObservableList<Patient> getAll();
    ObservableList<Patient> getByQuery(String query);
    void closeConnection();
}
