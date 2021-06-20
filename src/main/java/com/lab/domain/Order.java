package com.lab.domain;

public class Order {

    private String id;
    private String id_drink;
    private Customer customer;
    private String amount;

    public Order(String id, String id_drink, Customer customer, String amount) {
        this.id = id;
        this.id_drink = id_drink;
        this.customer = customer;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_drink() {
        return id_drink;
    }

    public void setId_drink(String id_drink) {
        this.id_drink = id_drink;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + " Покупатель:" + customer.getId() + " " + customer.getName() + " " + amount;
    }
}
