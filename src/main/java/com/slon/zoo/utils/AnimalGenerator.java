package com.slon.zoo.utils;

import com.slon.zoo.models.Animal;
import com.slon.zoo.models.DomesticAnimal;
import com.slon.zoo.models.WildAnimal;

import java.util.Random;

/**
 * Created by Sergii on 28.03.2017.
 */
public class AnimalGenerator {
    private static Random rand = new Random();
//    private Animal animal;

    public AnimalGenerator() {
        System.out.println("animal generator created");
    }

    public Animal create() {
        switch (rand.nextInt(2)) {
            case 0:
                return new DomesticAnimal();
            default:
                return new WildAnimal();
        }
    }

//    public Animal getAnimal() {
//        throw new RuntimeException("look-up method for Spring");
//    }
}
