package com.example.a19526811_tranvannhan_thithuchanh.entity;

public class Book {
    private String key;
    private String name;

    public Book() {}

    public Book(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
