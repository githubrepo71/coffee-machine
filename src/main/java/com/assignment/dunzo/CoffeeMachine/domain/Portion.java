package com.assignment.dunzo.CoffeeMachine.domain;

public class Portion {

    private final Ingredient ingredient;

    private final int quantity;

    public Portion(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
