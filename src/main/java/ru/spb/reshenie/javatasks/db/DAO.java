package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.entity.Patient;

import java.util.LinkedList;

public interface DAO {
    LinkedList<Patient> getAll();
    LinkedList<Patient> getByQuery(String query);
    void closeConnection();
}
