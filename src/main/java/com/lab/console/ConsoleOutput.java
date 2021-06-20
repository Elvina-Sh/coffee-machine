package com.lab.console;


public interface ConsoleOutput<T> {

    void get(String id);

    void getAll();

    void delete(String id) ;

    void add(T t);

    void update(String id, String name);
}
