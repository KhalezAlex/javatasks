package ru.spb.reshenie.javatasks.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.spb.reshenie.javatasks.entity.Patient;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryWizard {
    // мапа с ключом- пропсом, в которому ведется поиск и списком значений, по которым в ней будет проводиться поиск
    private Map<String, LinkedList<String>> props;
    // список значений, по которым будет проводиться поиск
    private List<String> predList;


    public List<String> getPredList() {
        return predList;
    }

    public Map<String, LinkedList<String>> getProps() {
        return props;
    }

    public QueryWizard(String predicate) {
        props = new HashMap<>();
        props.put("num", new LinkedList<>());
        props.put("snils", new LinkedList<>());
        props.put("sex", new LinkedList<>());
        props.put("fio", new LinkedList<>());
        props.put("birthDate", new LinkedList<>());
        props.put("age", new LinkedList<>());
        props.put("policy", new LinkedList<>());
        props.put("finSource", new LinkedList<>());
        predList = Arrays.asList(predicate.split(" "));
        predList = predList
                .stream()
                .filter(p -> p.length() <= 256)
                .toList();
    }

    public ObservableList<Patient> collectQuery(ObservableList<Patient> patients) {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        result.addAll(numSearchResults(patients));
        result.addAll(snilsSearchResults(patients));
        result.addAll(sexSearchResults(patients));
        return result;
    }

    private ObservableList<Patient> numSearchResults(ObservableList<Patient> patients) {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        // Добавляем в список props по ключу num все значения, по которым будет проводиться фильтрация
        props.get("num").addAll(getNumSearchList());
        // Фильтруем в список result через все значения из списка мапы
        props.get("num")
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

    private ObservableList<Patient> snilsSearchResults(ObservableList<Patient> patients) {
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

    private ObservableList<Patient> sexSearchResults(ObservableList<Patient> patients) {
        ObservableList<Patient> result = FXCollections.observableArrayList();
        getSexSearchList()
                .forEach(p ->
                        result.addAll(patients
                                .stream()
                                .filter(patient -> patient.getSex().toLowerCase().contains(p.toLowerCase()))
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


}

//    private static boolean instanceOfInt(String str) {
//        String snilsRegex = "\\d{3}-\\d{3}-\\d{3}-\\d{2}";
//        String numRegex = "\\d{6}";
//        String policyRegex = "\\d{20}";
//        if (Pattern.compile(snilsRegex).matcher(str).matches())
//            System.out.println("policy");
//        if (Pattern.compile(numRegex).matcher(str).matches())
//            System.out.println("7 dig");
//        if (Pattern.compile(policyRegex).matcher(str).matches())
//            System.out.println("20 dig");
//        return true;
//    }