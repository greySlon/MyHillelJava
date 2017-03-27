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

    public static Animal create() {
        switch (rand.nextInt(2)) {
            case 0:
                return new DomesticAnimal();
            default:
                return new WildAnimal();
        }
    }
}
