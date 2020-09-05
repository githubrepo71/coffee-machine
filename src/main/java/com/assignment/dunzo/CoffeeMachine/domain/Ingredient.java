package com.assignment.dunzo.CoffeeMachine.domain;

public class Ingredient {

    public final String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Define proper equals and hashcode method so that Ingredient can work as a key in a map.
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient)) return false;
        Ingredient that = (Ingredient) o;
        return this.name.equals(that.name);
    }
}