package com.lab.dao;

import com.lab.domain.Drink;
import com.lab.domain.SetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lab.ConnectionSource.*;

public class DrinkDao extends Dao<Drink> {

    private String tableName = "drink";
    private String columnLabelId = String.format(columnLabelIdTemplate, tableName);
    private String columnLabelName = String.format(columnLabelNameTemplate, tableName);
    private String columnLabelPrice= "price_" + tableName;
    private String getAll = String.format(getAllTemplate, tableName);
    private Map<String, Drink> map;
    private MapperFactory mapperFactory = new MapperFactory();

    public DrinkDao() {
        getAll();
    }

    @Override
    public Drink get(String id) {
        Drink drink = null;
        try {
            connection();
            resultSet = statement.executeQuery(getAll);
            map = mapperFactory.mapSet(resultSet);
            drink = map.get(id);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (drink == null)
            System.out.println("Нет такого напитка в базе!");
        return drink;
    }

    @Override
    public Map<String, Drink> getAll() {
        try {
            connection();
            resultSet = statement.executeQuery(getAll);
            map = mapperFactory.mapSet(resultSet);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean add(Drink drink) {
        try {
            connection();
            statement.execute(String.format(addTemplate, tableName, drink.getId(), drink.getName() +
                    "," + drink.getPrice()));
            map.put(drink.getId(), drink);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try {
            connection();
            statement.execute(String.format(deleteTemplate, tableName, tableName, id));
            map.remove(id);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(String id, String newName) {
        try {
            connection();
            statement.execute(String.format(updateTemplate,tableName,columnLabelName, newName, columnLabelId, id));
            map.get(id).setName(newName);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    private ArrayList<String> getRow(ResultSet row) {
        ArrayList<String> listOfRow = new ArrayList<>();
        try {
            listOfRow.add(row.getString(columnLabelId));
            listOfRow.add(row.getString(columnLabelName));
            listOfRow.add(row.getString(columnLabelPrice));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfRow;
    }

    private Drink fromRow(ArrayList<String> record) {
        return new Drink(record.get(0), record.get(1), record.get(2));
    }

    private class MapperFactory implements SetMapper<Map<String,Drink>> {

        @Override
        public Map<String, Drink> mapSet(ResultSet resultSet) {
            map = new HashMap<>();
            try{
                while(resultSet.next()) {
                    map.put(resultSet.getString(columnLabelId), fromRow(getRow(resultSet)));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return map;
        }
    }
}
