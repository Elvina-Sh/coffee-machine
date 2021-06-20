package com.lab.domain;

import java.sql.ResultSet;

public interface SetMapper<T> {
    T mapSet(ResultSet resultSet);
}
