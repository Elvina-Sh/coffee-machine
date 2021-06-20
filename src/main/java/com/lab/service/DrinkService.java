package com.lab.service;

import com.lab.dao.DrinkDao;
import com.lab.domain.Drink;

import java.util.Map;

public class DrinkService implements Service<Drink> {

    private DrinkDao drinkDao = new DrinkDao();

    @Override
    public Drink get(String id) {
        return drinkDao.get(id);
    }

    @Override
    public Map<String, Drink> getAll() {
        return drinkDao.getAll();
    }

    @Override
    public boolean update(String id, String newName) {
        return drinkDao.update(id, newName);
    }

    @Override
    public boolean add(Drink drink) {
        return drinkDao.add(drink);
    }

    @Override
    public boolean delete(String id) {
        return drinkDao.delete(id);
    }
}
