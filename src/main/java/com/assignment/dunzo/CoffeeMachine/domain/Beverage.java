package com.assignment.dunzo.CoffeeMachine.domain;

import java.util.Collection;

public class Beverage {

    private final String name;

    private final Collection<Portion> portions;

    public Beverage(String name, Collection<Portion> portions) {
        this.name = name.toLowerCase();
        this.portions = portions;
    }


    public String getName() {
        return name;
    }

    public Collection<Portion> getPortions() {
        return this.portions;
    }
}
