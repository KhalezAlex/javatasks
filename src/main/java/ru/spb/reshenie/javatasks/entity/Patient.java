package ru.spb.reshenie.javatasks.entity;

import java.time.LocalDate;

public class Patient {
    private Integer id;
    private String fio;
    private LocalDate birthDate;
    private String sex;
    private Integer num;
    private String smo;
    private String snils;
    private String policy;
    private Integer finSource;
    private Integer age;

    public Integer getId() {
        return id;
    }

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

    public String getSmo() {
        return smo;
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

    public Patient(Integer id, String fio, Integer num) {
        this.id = id;
        this.fio = fio;
        this.num = num;
    }

    public Patient(Integer id, String fio, LocalDate birthDate, Integer sex,
                   Integer num, String smo, String snils, String policy, Integer finSource) {
        this.id = id;
        this.fio = fio;
        this.birthDate = birthDate;
        this.sex = (sex == 1) ? "МУЖ" : "ЖЕН";
        this.num = num;
        this.smo = smo;
        this.snils = snils;
        this.policy = policy;
        this.finSource = finSource;
        this.age = LocalDate.now().compareTo(birthDate);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", num=" + num +
                ", smo='" + smo + '\'' +
                ", snils='" + snils + '\'' +
                ", policy='" + policy + '\'' +
                ", finSource=" + finSource +
                ", age=" + age +
                "}\n";
    }
}
