package com.mydemo.design.model.decorate;

public abstract class Sweet {

    private String description = "Sweet";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
