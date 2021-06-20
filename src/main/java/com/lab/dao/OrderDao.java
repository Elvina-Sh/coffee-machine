package com.lab.dao;

import com.lab.domain.Customer;
import com.lab.domain.Order;
import com.lab.domain.SetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lab.ConnectionSource.*;

public class OrderDao extends Dao<Order> {

    private String tableName = "coffee_order";
    private String columnLabelIdOrder = "id_order";
    private String columnLabelIdDrink = "id_drink";
    private String columnLabelIdCustomer = "id_customer";
    private String columnLabelAmount = "amount";
    private String getAll = String.format(getAllTemplate, tableName);
    private String insert = "INSERT INTO coffee_order VALUES(%s, %s, %s, %s)";
    private Map<String, Order> map;
    private MapperFactory mapperFactory = new MapperFactory();
    private CustomerDao customerDao = new CustomerDao();

    public OrderDao() {
        getAll();
    }

    @Override
    public Order get(String id) {
        Order order = null;
        try {
            connection();
            resultSet = statement.executeQuery(getAll);
            map = mapperFactory.mapSet(resultSet);
            order = map.get(id);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (order == null)
            System.out.println("Нет такого заказа в базе!");
        return order;
    }

    @Override
    public Map<String, Order> getAll() {
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
    public boolean add(Order order) {
        try {
            connection();
            statement.execute(String.format(insert, order.getId(), order.getId_drink(),
                    order.getCustomer().getId(), order.getAmount()));
            map.put(order.getId(), order);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        try {
            connection();
            statement.execute(String.format(deleteTemplate, tableName, "order", id));
            map.remove(id);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(String id, String amount) {
        try {
            connection();
            statement.execute(String.format(updateTemplate,tableName,columnLabelAmount, amount, columnLabelIdOrder, id));
            map.get(id).setAmount(amount);
            closeDB();
        } catch (SQLException|ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    private ArrayList<String> getRow(ResultSet row) {
        ArrayList<String> listOfRow = new ArrayList<>();
        try {
            listOfRow.add(row.getString(columnLabelIdOrder));
            listOfRow.add(row.getString(columnLabelIdDrink));
            listOfRow.add(row.getString(columnLabelIdCustomer));
            listOfRow.add(row.getString(columnLabelAmount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfRow;
    }

    private Order fromRow(ArrayList<String> record) {
        return new Order(record.get(0), record.get(1), getCustomer(record.get(2)), record.get(3));
    }


    private Customer getCustomer(String id) {
        return customerDao.get(id);
    }

    private class MapperFactory implements SetMapper<Map<String,Order>> {

        @Override
        public Map<String, Order> mapSet(ResultSet resultSet) {
            map = new HashMap<>();
            try{
                while(resultSet.next()) {
                    map.put(resultSet.getString(columnLabelIdOrder), fromRow(getRow(resultSet)));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return map;
        }
    }
}
