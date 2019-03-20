package com.msvdaamen.items;

public enum ModIngots {
    COPPER(0, "Copper"),
    TIN(1, "Tin"),
    BRONZE(2, "Bronze");

    private int id;
    private String name;

    ModIngots(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

}
