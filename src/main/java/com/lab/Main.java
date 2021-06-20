package com.lab;

import com.lab.console.ConsoleOutput;
import com.lab.console.CustomerConsoleOutput;
import com.lab.console.DrinkConsoleOutput;
import com.lab.console.OrderConsoleOutput;
import com.lab.domain.Customer;
import com.lab.domain.Drink;
import com.lab.domain.Order;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ConsoleOutput consoleOutput = null;
        int table;
        int option;
        boolean flag = true;

        while(flag) {
            System.out.println("Выберите таблицу \n 1 - Покупатель \n 2 - Заказ \n 3 - Напитки" +
                    "\n 4 - Выйти");

            table = sc.nextInt();

            switch (table) {
                case (1):
                    consoleOutput = new CustomerConsoleOutput();
                    break;
                case (2):
                    consoleOutput = new OrderConsoleOutput();
                    break;
                case (3):
                    consoleOutput = new DrinkConsoleOutput();
                    break;
                case (4):
                    flag = false;
                    break;
                default:
                    tableValidation(table);
            }

            if (!flag)
                break;

            System.out.println("Выберите действие \n 1 - Вывод всей таблицы \n 2 - Вывод записи по id" +
                    "\n 3 - Обновить запись \n 4 - Создать запись в таблице \n 5 - Удалить запись" +
                    "\n 6 - Вернуться к выбору таблицы \n 7 - Выйти");

            option = sc.nextInt();

            switch (option) {
                case (1):
                    consoleOutput.getAll();
                    break;
                case (2):
                    System.out.println("Введите id записи для вывода");
                    consoleOutput.get(sc.next());
                    break;
                case (3):
                    System.out.println("Введите id записи и новое значение");
                    consoleOutput.update(sc.next(), sc.next());
                    break;
                case (4):
                    System.out.println("Введите данные для создания записи");
                    consoleOutput.add(create(sc, table));
                    break;
                case (5):
                    System.out.println("Введите id записи для удаления");
                    consoleOutput.delete(sc.next());
                    break;
                case (6):
                    break;
                case (7):
                    flag = false;
                    break;
                default:
                    System.out.println("Выберите верное действие");
                    break;
            }

            if(!flag)
                break;
        }
    }

    private static Object create(Scanner sc, int i) {
        switch (i) {
            case 1:
                System.out.println("Покупатель: id и имя:");
                return new Customer(sc.next(), sc.next());
            case 2:
                System.out.println("Заказ: id заказа, id напитка, покупатель(id и имя) и сумма:");
                return new Order(sc.next(), sc.next(), new Customer(sc.next(), sc.next()), sc.next());
            case 3:
                System.out.println("Напиток: id, название и цена:");
                return new Drink(sc.next(), sc.next(), sc.next());
        }
        return null;
    }

    private static void tableValidation(int i) {
        if(i != 1 || i != 2 || i!= 3)
            throw new IllegalArgumentException("Выбрана не существующая таблица");
    }
}
