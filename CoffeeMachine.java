package machine;

import java.util.Scanner;

public class CoffeeMachine {
    int water;
    int milk;
    int coffee;
    int cups;
    int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.cups = 9;
        this.money = 550;
    }

    void buy(String choose) {
        switch (choose) {
            case "1":
                water -= 250;
                coffee -= 16;
                money += 4;
                cups--;
                break;
            case "2":
                water -= 350;
                milk -= 75;
                coffee -= 20;
                money += 7;
                cups--;
                break;
            case "3":
                water -= 200;
                milk -= 100;
                coffee -= 12;
                money += 6;
                cups--;
                break;
        }
    }

    void fill(int addWater, int addMilk, int addCoffee, int addCups) {
        water += addWater;
        milk += addMilk;
        coffee += addCoffee;
        cups += addCups;
    }

    int take() {
        int given = money;
        money = 0;
        return given;
    }

    String state() {
        return String.format("\nThe coffee machine has:%n" +
                "%d of water%n" +
                "%d of milk%n" +
                "%d of coffee beans%n" +
                "%d of disposable cups%n" +
                "$%d of money%n%n", water, milk, coffee, cups, money);
    }

    String possibleCups(String cupOfCoffee) {
        if (cups == 0) {
            return "Sorry, not enough cups!";
        }

        switch (cupOfCoffee) {
            case "1":
                if (water - 250 < 0) {
                    return "Sorry, not enough water!";
                } else if (coffee - 16 < 0) {
                    return "Sorry, not enough coffee beans!";
                }
                break;
            case "2":
                if (water - 350 < 0) {
                    return "Sorry, not enough water!";
                } else if (milk - 75 < 0) {
                    return "Sorry, not enough milk!";
                } else if (coffee - 20 < 0) {
                    return "Sorry, not enough coffee beans!";
                }
                break;
            case "3":
                if (water - 200 < 0) {
                    return "Sorry, not enough water!";
                } else if (milk - 100 < 0) {
                    return "Sorry, not enough milk!";
                } else if (coffee - 12 < 0) {
                    return "Sorry, not enough coffee beans!";
                }
                break;
        }
        return "OK";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = sc.next();

            if (action.equals("exit")) {
                break;
            }

            switch (action) {
                case "buy":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String option = sc.next();
                    if (option.equals("back")) {
                        break;
                    }

                    String result = coffeeMachine.possibleCups(option);
                    if (!result.equals("OK")) {
                        System.out.println(result);
                        break;
                    }

                    System.out.println("I have enough resources, making you a coffee!\n");
                    coffeeMachine.buy(option);
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    int addWater = sc.nextInt();
                    System.out.println("Write how many ml of milk do you want to add:");
                    int addMilk = sc.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    int addCoffee = sc.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    int addCups = sc.nextInt();
                    System.out.println();

                    coffeeMachine.fill(addWater, addMilk, addCoffee, addCups);
                    break;
                case "take":
                    System.out.printf("%nI gave you $%d%n%n", coffeeMachine.take());
                    break;
                case "remaining":
                    System.out.print(coffeeMachine.state());
                    break;
            }
        }
    }
}
