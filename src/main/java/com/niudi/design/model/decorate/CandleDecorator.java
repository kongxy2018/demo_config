package com.niudi.design.model.decorate;

/**
 * 具体装饰者蜡烛类
 */
public class CandleDecorator extends Decorator{

    Sweet sweet;

    public CandleDecorator(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String getDescription() {
        return sweet.getDescription() + "加了十根蜡烛";
    }

    @Override
    public double cost() {
        return sweet.cost() + 8;
    }
}
