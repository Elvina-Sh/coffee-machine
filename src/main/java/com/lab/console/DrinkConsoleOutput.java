package com.lab.console;

import com.lab.domain.Drink;
import com.lab.service.DrinkService;

public class DrinkConsoleOutput implements ConsoleOutput<Drink> {

    private DrinkService drinkService = new DrinkService();

    @Override
    public void get(String id) {
        System.out.println(drinkService.get(id));
    }

    @Override
    public void getAll() {
        drinkService.getAll().forEach((id, Drink) -> System.out.println(Drink));
    }

    @Override
    public void delete(String id) {
        if(drinkService.delete(id))
            System.out.println("Напиток удален");
    }

    @Override
    public void add(Drink drink) {
        if(!drinkService.add(drink))
            System.out.println("Такой напиток уже существует!");
    }

    @Override
    public void update(String id, String name) {
        drinkService.update(id, name);
    }
}
