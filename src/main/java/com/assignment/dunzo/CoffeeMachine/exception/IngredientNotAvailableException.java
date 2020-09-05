package com.assignment.dunzo.CoffeeMachine.exception;

import com.assignment.dunzo.CoffeeMachine.domain.Ingredient;

public class IngredientNotAvailableException extends Exception {

    private final Ingredient ingredient;

    public IngredientNotAvailableException(String message, Ingredient ingredient) {
        super(message);
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }
}
