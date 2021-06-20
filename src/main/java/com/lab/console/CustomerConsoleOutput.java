package com.lab.console;

import com.lab.domain.Customer;
import com.lab.service.CustomerService;


public class CustomerConsoleOutput implements ConsoleOutput<Customer> {

    private CustomerService customerService = new CustomerService();

    @Override
    public void get(String id) {
        System.out.println(customerService.get(id));
    }

    @Override
    public void getAll() {
        customerService.getAll().forEach((id, customer) -> System.out.println(customer));
    }

    @Override
    public void delete(String id) {
        if(customerService.delete(id))
            System.out.println("Пользователь удален");
    }

    @Override
    public void add(Customer customer) {
        if(!customerService.add(customer))
            System.out.println("Такой покупатель уже существует!");
    }

    @Override
    public void update(String id, String newName) {
        customerService.update(id, newName);
    }
}
