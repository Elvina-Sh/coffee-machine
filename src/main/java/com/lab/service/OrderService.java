package com.lab.service;

import com.lab.dao.OrderDao;
import com.lab.domain.Order;

import java.util.Map;

public class OrderService implements Service<Order> {

    private OrderDao orderDao = new OrderDao();

    @Override
    public Order get(String id) {
        return orderDao.get(id);
    }

    @Override
    public Map<String, Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean update(String id, String newName) {
        return orderDao.update(id, newName);
    }

    @Override
    public boolean add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public boolean delete(String id) {
        return orderDao.delete(id);
    }
}
