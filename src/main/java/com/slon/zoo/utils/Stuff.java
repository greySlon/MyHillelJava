package com.slon.zoo.utils;

import com.slon.zoo.models.*;

import java.util.List;

/**
 * Created by Sergii on 28.03.2017.
 */
public class Stuff {
    Doctor doctor = new Doctor();
    Supervisor supervisor = new Supervisor();
    Groomer groomer = new Groomer();
    FoodManager foodManager = new FoodManager();
    FoodManager foodManager2 = new FoodManager();

    public void registerAnimal(Animal animal) {
        animal.subscribeOnDiseaseEvent(doctor.getDiseaseEventListener());
        animal.subscribeOnDiseaseEvent(supervisor.getDiseaseEventListener());


        animal.subscribeOnWannaEatEvent(foodManager.getEatEventListener());
        animal.subscribeOnWannaEatEvent(doctor.getEatEventListener());
        animal.subscribeOnWannaEatEvent(supervisor.getEatEventListener());

        if (animal.getClass() == DomesticAnimal.class) {
            DomesticAnimal domesticAnimal = ((DomesticAnimal) animal);
            domesticAnimal.subscribeOnFurCareEvent(groomer.getFurCareEventListener());
            domesticAnimal.subscribeOnFurCareEvent(supervisor.getFurCareEventListener());
        }
    }

    public void addFoodManager(List<Animal> animalList) {
        for (Animal animal : animalList) {
            if(animal.getClass()==WildAnimal.class){
                animal.unsubscribeFromWannaEatEvent(foodManager.getEatEventListener());
                animal.subscribeOnWannaEatEvent(foodManager2.getEatEventListener());
            }
        }
    }
}
