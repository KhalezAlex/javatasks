package ru.spb.reshenie.javatasks.entity;

import java.time.LocalDate;

import static ru.spb.reshenie.javatasks.utils.PatientAdapter.*;

public class Patient {
    private Integer num;
    private String snils;
    private String sex;
    private String fio;
    private String fioAbbr;
    private String birthDate;
    private String age;
    private String policy;
    private Integer finSource;


    public String getFio() {
        return fio;
    }

    public String getFioAbbr() {
        return fioAbbr;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public Integer getNum() {
        return num;
    }

    public String getSnils() {
        return snils;
    }

    public String getPolicy() {
        return policy;
    }

    public Integer getFinSource() {
        return finSource;
    }

    public String getAge() {
        return age;
    }

    public Patient() {
    }

    public Patient(String fio, LocalDate birthDate, Integer sex,
                   Integer num, String smo, String snils, String policy, Integer finSource) {
        this.fio = fio;
        this.fioAbbr = getFioAbbrStr(fio);
        this.birthDate = getDateFromSQLFormat(birthDate);
        this.sex = getSexStr(sex);
        this.num = num;
        this.snils = getSnilsStr(snils);
        this.policy = getPolicyStr(smo, policy);
        this.finSource = finSource;
        this.age = getAgeStr(birthDate);
    }

    @Override
    public String toString() {
        return "Patient{" +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", num=" + num +
                ", snils='" + snils + '\'' +
                ", policy='" + policy + '\'' +
                ", finSource=" + finSource +
                ", age=" + age +
                "}\n";
    }
}
