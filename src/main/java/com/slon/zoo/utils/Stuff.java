package com.slon.zoo.utils;

import com.slon.zoo.models.*;

import java.util.List;

/**
 * Created by Sergii on 28.03.2017.
 */
public class Stuff {
    Doctor doctor;// = new Doctor();
    Supervisor supervisor;// = new Supervisor();
    Groomer groomer;// = new Groomer();
    FoodManager foodManager;// = new FoodManager();
    FoodManager foodManager2;// = new FoodManager();

    Animal animal;

    public Stuff() {
        System.out.println("stuff created");
    }

    public Animal getAnimal() {
        throw new RuntimeException("look-up method for Spring");
    }

    public Animal registerAnimal(/*Animal animal*/) {
        Animal animal = getAnimal();
//        animal.subscribeOnDiseaseEvent(doctor.getDiseaseEventListener());
//        animal.subscribeOnDiseaseEvent(supervisor.getDiseaseEventListener());
//
//
//        animal.subscribeOnWannaEatEvent(foodManager.getEatEventListener());
//        animal.subscribeOnWannaEatEvent(doctor.getEatEventListener());
//        animal.subscribeOnWannaEatEvent(supervisor.getEatEventListener());

//        if (animal.getClass() == DomesticAnimal.class) {
//            DomesticAnimal domesticAnimal = ((DomesticAnimal) animal);
//            domesticAnimal.subscribeOnFurCareEvent(groomer.getFurCareEventListener());
//            domesticAnimal.subscribeOnFurCareEvent(supervisor.getFurCareEventListener());
//        }
        return animal;
    }

//    public void addFoodManager(List<Animal> animalList) {
//        for (Animal animal : animalList) {
//            if (animal.getClass() == WildAnimal.class) {
//                animal.unsubscribeFromWannaEatEvent(foodManager.getEatEventListener());
//                animal.subscribeOnWannaEatEvent(foodManager2.getEatEventListener());
//            }
//        }
//    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public void setGroomer(Groomer groomer) {
        this.groomer = groomer;
    }

    public void setFoodManager(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    public void setFoodManager2(FoodManager foodManager2) {
        this.foodManager2 = foodManager2;
    }
}
