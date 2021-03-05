package com.mydemo.design.model.decorate;

public class Test {
    public static void main(String[] args) {
        Cake cake = new Cake();
        System.out.println("一个蛋糕花费" + cake.cost());

        FruitDecorator fruitDecorator = new FruitDecorator(cake);
        System.out.println("一个水果蛋糕花费" + fruitDecorator.cost());

        CandleDecorator candleDecorator = new CandleDecorator(cake);
        System.out.println("一个蜡烛蛋糕花费" + candleDecorator.cost());
    }
}
