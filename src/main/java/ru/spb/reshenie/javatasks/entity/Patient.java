package ru.spb.reshenie.javatasks.entity;

import java.time.LocalDate;

import static ru.spb.reshenie.javatasks.utils.PatientAdapter.*;

public class Patient {
    private final Integer num;
    private final String snils;
    private final String sex;
    private final String fio;
    private final String fioAbbr;
    private final String birthDate;
    private final String age;
    private final String policy;
    private final Integer finSource;


    public String getFio() {
        return fio;
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
