package com.slon.zoo.utils;

import com.slon.zoo.models.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergii on 28.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        List<Animal> animalList = new ArrayList<>();

        Stuff stuff = new Stuff();

        for (int i = 0; i < 10; i++) {
            Animal animal = AnimalGenerator.create();
            stuff.registerAnimal(animal);
            animalList.add(animal);
        }

        for (Animal someAnimal : animalList) {
            System.out.println("************");
            someAnimal.eventGenerator();
            Thread.sleep(1000);
        }

        System.out.println("\n******Add FoodManager more************\n");

        stuff.addFoodManager(animalList);

        for (Animal someAnimal : animalList) {
            System.out.println("************");
            someAnimal.eventGenerator();
            Thread.sleep(1000);
        }
    }
}
