package com.assignment.dunzo.CoffeeMachine.service;

import com.assignment.dunzo.CoffeeMachine.domain.Ingredient;
import com.assignment.dunzo.CoffeeMachine.domain.Portion;
import com.assignment.dunzo.CoffeeMachine.exception.InsufficientIngredientQuantityException;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsumablePortion extends Portion {

    private final AtomicInteger quantity;

    public ConsumablePortion(Ingredient ingredient, int quantity) {
       super(ingredient, quantity);
       this.quantity = new AtomicInteger(quantity);
    }

    /**
     * Atomically consumes a quantity from this portion
     * @param quantity quantity to consume
     * @throws InsufficientIngredientQuantityException when not enough quantity
     */
    public void consume(int quantity) throws InsufficientIngredientQuantityException {
        // update the quantity after consuming
        int previous = this.quantity.getAndUpdate(x -> x >= quantity ? x - quantity : x);

        if(previous - quantity < 0) throwInsufficientException(previous);
    }

    public int getQuantity() {
        return this.quantity.intValue();
    }

    public void ensureQuantity(int required) throws InsufficientIngredientQuantityException {
        int currentQuantity = this.getQuantity();
        if(currentQuantity < required) throwInsufficientException(currentQuantity);
    }

    private void throwInsufficientException(int currentQuantity) throws InsufficientIngredientQuantityException {
        throw new InsufficientIngredientQuantityException(
                this.getIngredient().getName() + " is not available", new Portion(this.getIngredient(), currentQuantity));
    }
}
