package com.slon.zoo;

import com.slon.zoo.models.Animal;
import com.slon.zoo.utils.AnimalGenerator;
import com.slon.zoo.utils.Stuff;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergii on 28.03.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx=new GenericXmlApplicationContext("context.xml");
        Stuff stuff = (Stuff)ctx.getBean("stuff");
        List<Animal> animalList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
//            Animal animal = generator.create();
            Animal animal = stuff.registerAnimal();
            animalList.add(animal);
        }

        for (Animal someAnimal : animalList) {
            System.out.println("************");
            someAnimal.eventGenerator();
//            Thread.sleep(1000);
        }
//
//        System.out.println("\n******Add FoodManager more************\n");
//
//        stuff.addFoodManager(animalList);
//
//        for (Animal someAnimal : animalList) {
//            System.out.println("************");
//            someAnimal.eventGenerator();
//            Thread.sleep(1000);
//        }
    }
}
