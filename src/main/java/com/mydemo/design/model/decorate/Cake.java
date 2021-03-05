package com.mydemo.design.model.decorate;


public class Cake extends Sweet{

    @Override
    public String getDescription() {
        return "One Cake";
    }

    @Override
    public double cost() {
        return 66;
    }
}
