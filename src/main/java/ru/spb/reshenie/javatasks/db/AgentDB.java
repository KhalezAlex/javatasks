package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.utils.ConfigReader;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;

public class AgentDB {
    private final Connection connection;

    public AgentDB() {
        try {
            Map<String, String> config = ConfigReader.dbUrl();
            Class.forName(config.get("DB_DRIVER"));
            this.connection = DriverManager.getConnection(config.get("DB_URL"),
                    config.get("username"), config.get("password"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AgentDB(String url, String name, String password) {
        try {
            Map<String, String> config = ConfigReader.dbUrl();
            Class.forName(config.get("DB_DRIVER"));
            this.connection = DriverManager.getConnection(url, name, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Patient> getAll() {
        String query = "SELECT * FROM java_tasks_patient";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            return getFromResultSet(result);
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LinkedList<Patient> getFromResultSet(ResultSet result) throws SQLException, ParseException {
        LinkedList<Patient> patients = new LinkedList<>();
        while (result.next()) {
            patients.add(getFromResult(result));
        }
        return patients;
    }

    private Patient getFromResult(ResultSet result) throws SQLException{
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
    }

    public LocalDate getDate(String date) {
        String[] birthDate = date.split("-");
        return LocalDate.of(Integer.parseInt(birthDate[0]),
                Integer.parseInt(birthDate[1]), Integer.parseInt(birthDate[2]));
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}