package com.entity;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public User(Integer id, String name, Integer age, Integer score, Integer money) {
        //注意要按顺序选中
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", money=" + money +
                '}';
    }

    public User() {
    }
}
