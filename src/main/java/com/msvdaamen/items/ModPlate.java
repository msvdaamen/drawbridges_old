package com.msvdaamen.items;

public enum ModPlate {
    COPPER(0, "Copper"),
    TIN(1, "Tin"),
    BRONZE(2, "Bronze"),
    IRON(3, "Iron"),
    GOLD(4, "Gold");

    private int id;
    private String name;

    ModPlate(int id, String name) {
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
