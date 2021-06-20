package com.lab.console;

import com.lab.domain.Order;
import com.lab.service.OrderService;

public class OrderConsoleOutput implements ConsoleOutput<Order> {
    private OrderService orderService = new OrderService();

    @Override
    public void get(String id) {
        System.out.println(orderService.get(id));
    }

    @Override
    public void getAll() {
        orderService.getAll().forEach((id, order) -> System.out.println(order));
    }

    @Override
    public void delete(String id) {
        if(orderService.delete(id))
            System.out.println("Напиток удален");
    }

    @Override
    public void add(Order order) {
        if(!orderService.add(order))
        System.out.println("Такой заказ уже существует!");
    }

    @Override
    public void update(String id, String name) {
        orderService.update(id, name);
    }
}
