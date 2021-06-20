package com.lab.dao;

import com.lab.domain.Customer;
import com.lab.domain.SetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.lab.ConnectionSource.*;

public class CustomerDao extends Dao<Customer> {

    private String tableName = "customer";
    private String columnLabelId = String.format(columnLabelIdTemplate, tableName);
    private String columnLabelName = String.format(columnLabelNameTemplate, tableName);
    private String getAll = String.format(getAllTemplate, tableName);
    private Map<String, Customer> map;
    private MapperFactory mapperFactory = new MapperFactory();

    public CustomerDao() {
        getAll();
    }

    @Override
    public Customer get(String id) {
        Customer customer = null;
        try {
            connection();
            resultSet = statement.executeQuery(getAll);
            map = mapperFactory.mapSet(resultSet);
            customer = map.get(id);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (customer == null)
            System.out.println("Нет такого покупателя в базе!");
        return customer;
    }

    @Override
    public Map<String, Customer> getAll() {
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
    public boolean add(Customer customer) {
        try {
            connection();
            statement.execute(String.format(addTemplate, tableName, customer.getId(), customer.getName()));
            map.put(customer.getId(), customer);
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
            statement.execute(String.format(updateTemplate,tableName,columnLabelName,newName,columnLabelId, id));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfRow;
    }

    private Customer fromRow(ArrayList<String> record) {
        return new Customer(record.get(0), record.get(1));
    }

    private class MapperFactory implements SetMapper<Map<String,Customer>>{

        @Override
        public Map<String, Customer> mapSet(ResultSet resultSet) {
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
