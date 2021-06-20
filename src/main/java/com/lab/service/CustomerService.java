package com.lab.service;

import com.lab.dao.CustomerDao;
import com.lab.domain.Customer;

import java.util.Map;

public class CustomerService implements Service<Customer>{

    private CustomerDao customerDao = new CustomerDao();

    @Override
    public Customer get(String id) {
        return customerDao.get(id);
    }

    @Override
    public Map<String, Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public boolean update(String id, String newName) {

        return customerDao.update(id, newName);
    }

    @Override
    public boolean add(Customer customer) {
        return customerDao.add(customer);
    }

    @Override
    public boolean delete(String id) {
        return customerDao.delete(id);
    }

}
