package ru.spb.reshenie.javatasks.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.spb.reshenie.javatasks.entity.Patient;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryWizard {
    // список значений, по которым будет проводиться поиск
    private List<String> predList;
    private ObservableList<Patient> patients;

    public QueryWizard(String predicate, ObservableList<Patient> patients) {
        this.patients = patients;
        predList = Arrays.asList(predicate.split(" "));
        predList = predList
                .stream()
                .filter(p -> p.length() <= 256)
                .toList();
    }

    public ObservableList<Patient> collectQuery() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        result.addAll(numSearchResults());
        result.addAll(snilsSearchResults());
        result.addAll(sexSearchResults());
        result.addAll(fioSearchResults());
        result.addAll(dateOfBirthSearchResults());
        result.addAll(ageSearchResults());
        result.addAll(policySearchList());
        result.addAll(finSourceSearchList());
        return result;
    }

    private ObservableList<Patient> numSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        // Фильтруем в список result через все значения из списка на поиск
        getNumSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getNum().toString().contains(p))
                                .toList()));
        return result;
    }

    private List<String> getNumSearchList() {
        return predList
                .stream()
                .filter(num ->
                        Pattern
                                .compile("^\\d{1,6}")
                                .matcher(num)
                                .matches())
                .collect(Collectors.toList());
    }

    private ObservableList<Patient> snilsSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getSnilsSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getSnils().contains(p))
                                .toList()));
        return result;
    }

    private List<String> getSnilsSearchList() {
        return predList
                .stream()
                .filter(snils ->
                        Pattern.compile("^\\d{1,11}")
                                .matcher(Arrays.stream(snils.split("-"))
                                        .reduce(String::concat)
                                        .get())
                                .matches())
                .collect(Collectors.toList());
    }

    private ObservableList<Patient> sexSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getSexSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getSex().toLowerCase().contains(p.toLowerCase()))
                                .toList()));
        return result;
    }

    private List<String> getSexSearchList() {
        return predList
                .stream()
                .filter(sex ->
                        Pattern.compile("^[е-уЕ-У]{1,3}")
                                .matcher(sex)
                                .matches())
                .collect(Collectors.toList());
    }

    private ObservableList<Patient> fioSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getFioSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getFio().toLowerCase().contains(p.toLowerCase()))
                                .toList()));
        return result;
    }

    private List<String> getFioSearchList() {
        return predList
                .stream()
                .filter(fio ->
                        Pattern.compile("^[а-яА-Я]{1,20}")
                                .matcher(fio)
                                .matches())
                .toList();
    }

    private ObservableList<Patient> dateOfBirthSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        predList
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getBirthDate().contains(p))
                                .toList()));
        return result;
    }

    private List<String> getDateOfBirthSearchList() {
        return predList
                .stream()
                .filter(dob ->
                        Pattern.compile("d{2,4}")
                                .matcher(dob)
                                .matches())
                .toList();
    }

    private ObservableList<Patient> ageSearchResults() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getAgeSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient ->
                                        patient.getAge().contains(p))
                                .toList()));
        return result;
    }

    private List<String> getAgeSearchList() {
        return predList
                .stream()
                .filter(age ->
                        Pattern.compile("^\\d{1,2}")
                                .matcher(age)
                                .matches())
                .toList();
    }

    private ObservableList<Patient> policySearchList() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getPolicySearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter( patient ->
                                        patient.getPolicy().toLowerCase().contains(p.toLowerCase()))
                                .toList()));
        return result;
    }

    private List<String> getPolicySearchList() {
        List<String> temp = new ArrayList<>(predList
                .stream()
                .filter(p ->
                        Pattern.compile("^[а-яА-Я]{1,200}")
                                .matcher(p)
                                .matches())
                .toList());
        temp.addAll(predList
                .stream()
                .filter(p ->
                        Pattern.compile("^\\d{1,20}")
                                .matcher(p)
                                .matches())
                .toList());
        return temp;
    }

    private ObservableList<Patient> finSourceSearchList() {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        Map<Integer, String> finSources = new HashMap<>();
        finSources.put(1, "ОМС");
        finSources.put(2, "ДМС");
        finSources.put(3, "Наличный расчет");
        getFinSourceSearchList()
                        .forEach(p ->
                                result.addAll(patients
                                        .stream()
                                        .filter(patient ->
                                                finSources
                                                        .get(patient.getFinSource())
                                                        .toLowerCase()
                                                        .contains(p.toLowerCase()))
                                        .toList()));
        return result;
    }

    private List<String> getFinSourceSearchList() {
        return predList
                .stream()
                .filter(fs ->
                        Pattern.compile("^[а-яА-Я]{1,8}")
                                .matcher(fs)
                                .matches())
                .toList();
    }
}
