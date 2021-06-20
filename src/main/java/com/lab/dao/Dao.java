package com.lab.dao;

import java.util.Map;

public abstract class Dao<T> {

    String columnLabelIdTemplate = "id_%s";
    String columnLabelNameTemplate = "name_%s";
    String getAllTemplate = "SELECT * FROM %s";
    String addTemplate = "INSERT INTO %s VALUES(%s, \"%s\")";
    String deleteTemplate = "DELETE FROM %s WHERE id_%s = %s";
    String updateTemplate = "UPDATE %s SET %s = \"%s\" WHERE %s = %s";

    abstract T get(String id);

    abstract Map<String, T> getAll();

    abstract boolean add(T t);

    abstract boolean delete(String id);

    abstract boolean update(String id, String newName);

}
