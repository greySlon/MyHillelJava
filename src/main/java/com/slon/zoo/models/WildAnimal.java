package com.slon.zoo.models;

import java.util.Random;

/**
 * Created by Sergii on 27.03.2017.
 */
public class WildAnimal extends Animal {
    public WildAnimal() {
        System.out.println("wild animal created");
    }

    @Override
    public void eventGenerator() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        switch (i) {
            case 0:
                this.feelIll();
                break;
            default:
                this.wannaEat();
        }
    }
}
