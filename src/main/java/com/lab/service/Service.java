package com.lab.service;

import java.util.Map;

public interface Service<T> {

    T get(String id);

    Map<String,T> getAll();

    boolean update(String id, String newName);

    boolean add(T t);

    boolean delete(String id);

}
