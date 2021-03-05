package com.niudi.design.model.decorate;

/**
 * 具体装饰这水果类
 */
public class FruitDecorator extends Decorator{

    Sweet sweet;

    public FruitDecorator(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String getDescription() {
        return sweet.getDescription() + "加了一个水果";
    }

    @Override
    public double cost() {
        return sweet.cost() + 10;
    }
}
