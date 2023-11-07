package ru.spb.reshenie.javatasks.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.interfaces.DAO;
import ru.spb.reshenie.javatasks.utils.ConfigReader;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;

public class DbDao implements DAO {
    private final Connection connection;
    //  СДЕЛАТЬ НОРМАЛЬНЫЙ КОНФИГ
    public DbDao() {
        try {
            Map<String, String> config = ConfigReader.dbUrl();
            this.connection = DriverManager.getConnection(config.get("DB_URL"),
                    config.get("username"), config.get("password"));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DbDao(String url, String name, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Patient> getAll() {
        String query = "SELECT * FROM java_tasks_patient";
        return getByQuery(query);
    }

    @Override
    public ObservableList<Patient> getByQuery(String query) {
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return FXCollections.observableArrayList(getFromResultSet(result));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private LinkedList<Patient> getFromResultSet(ResultSet result) throws SQLException {
        LinkedList<Patient> patients = new LinkedList<>();
        while (result.next()) {
            patients.add(getFromResult(result));
        }
        result.close();
        return patients;
    }

    private Patient getFromResult(ResultSet result) {
        try {
            return new Patient(
                    result.getString("fio"),
                    getDate(result.getString("birth_date")),
                    Integer.parseInt(result.getString("sex")),
                    Integer.parseInt(result.getString("num")),
                    result.getString("smo"),
                    result.getString("snils"),
                    result.getString("policy"),
                    Integer.parseInt(result.getString("fin_source")
                    ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getDate(String date) {
        String[] birthDate = date.split("-");
        return LocalDate.of(Integer.parseInt(birthDate[0]),
                Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]));
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}