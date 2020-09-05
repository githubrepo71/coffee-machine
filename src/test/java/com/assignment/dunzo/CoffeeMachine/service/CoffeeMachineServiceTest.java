package com.assignment.dunzo.CoffeeMachine.service;

import com.assignment.dunzo.CoffeeMachine.domain.Beverage;
import com.assignment.dunzo.CoffeeMachine.domain.Ingredient;
import com.assignment.dunzo.CoffeeMachine.domain.Portion;
import com.assignment.dunzo.CoffeeMachine.exception.BeverageCannotBePreparedException;
import com.assignment.dunzo.CoffeeMachine.exception.IngredientNotAvailableException;
import com.assignment.dunzo.CoffeeMachine.exception.InsufficientIngredientQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeMachineServiceTest {

    private CoffeeMachine machine;

    @BeforeEach
    void instantiateMachine() {
        Ingredient hotWater = new Ingredient("hot_water");
        Ingredient hotMilk = new Ingredient("hot_milk");
        Ingredient gingerSyrup = new Ingredient("ginger_syrup");
        Ingredient sugarSyrup = new Ingredient("sugar_syrup");
        Ingredient teaLeavesSyrup = new Ingredient("tea_leaves_syrup");
        Ingredient greenMixture = new Ingredient("green_mixture");

        Beverage hotTea = new Beverage("hot_tea", Arrays.asList(
                new Portion(hotWater, 200),
                new Portion(hotMilk, 100),
                new Portion(gingerSyrup, 10),
                new Portion(sugarSyrup, 10),
                new Portion(teaLeavesSyrup, 10)
        ));

        Beverage hotCoffee = new Beverage("hot_coffee", Arrays.asList(
                new Portion(hotWater, 100),
                new Portion(gingerSyrup, 30),
                new Portion(hotMilk, 400),
                new Portion(sugarSyrup, 50),
                new Portion(teaLeavesSyrup, 30)
        ));

        Beverage blackTea = new Beverage("black_tea", Arrays.asList(
                new Portion(hotWater, 300),
                new Portion(gingerSyrup, 30),
                new Portion(sugarSyrup, 50),
                new Portion(teaLeavesSyrup, 30)
        ));

        Beverage greenTea = new Beverage("green_tea", Arrays.asList(
                new Portion(hotWater, 100),
                new Portion(gingerSyrup, 30),
                new Portion(sugarSyrup, 50),
                new Portion(greenMixture, 30)
        ));

        this.machine = new CoffeeMachine(Arrays.asList(
                new Portion(hotWater, 500),
                new Portion(hotMilk, 500),
                new Portion(gingerSyrup, 100),
                new Portion(sugarSyrup, 100),
                new Portion(teaLeavesSyrup, 100)
        ), Arrays.asList(hotTea, hotCoffee, blackTea, greenTea)
        );
    }

    @Test
    public void test1() throws BeverageCannotBePreparedException, IngredientNotAvailableException {
        Beverage tea = machine.prepareAndDispense("hot_tea");
        assertEquals("hot_tea", tea.getName());

        Beverage coffee = machine.prepareAndDispense("hot_coffee");
        assertEquals("hot_coffee", coffee.getName());

        IngredientNotAvailableException e = Assertions.assertThrows(IngredientNotAvailableException.class, () -> {
            machine.prepareAndDispense("green_tea");
        });
        assertEquals("green_mixture", e.getIngredient().getName());

        BeverageCannotBePreparedException e2 = Assertions.assertThrows(BeverageCannotBePreparedException.class, () -> {
            machine.prepareAndDispense("black_tea");
        });
        InsufficientIngredientQuantityException e3 = (InsufficientIngredientQuantityException) e2.getCause();
        assertEquals(200, e3.getCurrentPortion().getQuantity());
        assertEquals("hot_water", e3.getCurrentPortion().getIngredient().getName());
    }

    @Test
    public void test2() throws BeverageCannotBePreparedException, IngredientNotAvailableException {
        Beverage tea = machine.prepareAndDispense("hot_tea");
        assertEquals("hot_tea", tea.getName());

        Beverage blackTea = machine.prepareAndDispense("black_tea");
        assertEquals("black_tea", blackTea.getName());

        IngredientNotAvailableException e = Assertions.assertThrows(IngredientNotAvailableException.class, () -> {
            machine.prepareAndDispense("green_tea");
        });
        assertEquals("green_mixture", e.getIngredient().getName());

        BeverageCannotBePreparedException e2 = Assertions.assertThrows(BeverageCannotBePreparedException.class, () -> {
            machine.prepareAndDispense("hot_coffee");
        });
        InsufficientIngredientQuantityException e3 = (InsufficientIngredientQuantityException) e2.getCause();
        assertEquals(0, e3.getCurrentPortion().getQuantity());
        assertEquals("hot_water", e3.getCurrentPortion().getIngredient().getName());
    }

    @Test
    public void test3() throws BeverageCannotBePreparedException, IngredientNotAvailableException {
        Beverage tea = machine.prepareAndDispense("hot_coffee");
        assertEquals("hot_coffee", tea.getName());

        Beverage coffee = machine.prepareAndDispense("black_tea");
        assertEquals("black_tea", coffee.getName());

        IngredientNotAvailableException e = Assertions.assertThrows(IngredientNotAvailableException.class, () -> {
            machine.prepareAndDispense("green_tea");
        });
        assertEquals("green_mixture", e.getIngredient().getName());

        BeverageCannotBePreparedException e2 = Assertions.assertThrows(BeverageCannotBePreparedException.class, () -> {
            machine.prepareAndDispense("hot_tea");
        });
        InsufficientIngredientQuantityException e3 = (InsufficientIngredientQuantityException) e2.getCause();
        assertEquals(100, e3.getCurrentPortion().getQuantity());
        assertEquals("hot_water", e3.getCurrentPortion().getIngredient().getName());
    }


}