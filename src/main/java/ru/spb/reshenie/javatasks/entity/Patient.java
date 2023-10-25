package ru.spb.reshenie.javatasks.entity;

import java.time.LocalDate;

public class Patient {
    private Integer num;
    private String snils;
    private String sex;
    private String fio;
    private LocalDate birthDate;
    private Integer age;
    private String policy;
    private Integer finSource;


    public String getFio() {
        return fio;
    }

    public LocalDate getBirthDate() {
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

    public Integer getAge() {
        return age;
    }

    public Patient() {
    }

    public Patient(Integer id, String fio, LocalDate birthDate, Integer sex,
                   Integer num, String smo, String snils, String policy, Integer finSource) {
//        this.id = id;
        this.fio = fio;
        this.birthDate = birthDate;
        this.sex = (sex  == 1) ? "МУЖ" : "ЖЕН";
        this.num = num;
        this.snils = snils;
        this.policy = policy;
        this.finSource = finSource;
        this.age = LocalDate.now().compareTo(birthDate);
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
