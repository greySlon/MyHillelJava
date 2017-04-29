
package com.slon.zoo.models;

import com.slon.zoo.springEvents.DiseaseEvent;
import com.slon.zoo.springEvents.DiseaseEventListener;
import com.slon.zoo.springEvents.WannaEatEvent;
import com.slon.zoo.springEvents.WannaEatEventListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by Sergii on 27.03.2017.
 */
public class Doctor implements ApplicationListener<ApplicationEvent> {
    private String name = this.getClass().getCanonicalName().replaceAll("(\\S+\\.)*", "");


    public Doctor() {
        System.out.println("doctor created");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent.getClass()== WannaEatEvent.class)
            onWannaEatEvent(((WannaEatEvent) applicationEvent));
        else if (applicationEvent instanceof DiseaseEvent)
            onDiseaseEvent(((DiseaseEvent) applicationEvent));
    }

    private void onWannaEatEvent(WannaEatEvent wannaEatEvent) {
        System.out.println(name + ":* " + wannaEatEvent.toString());
    }


    private void onDiseaseEvent(DiseaseEvent diseaseEvent) {
        System.out.println(name + ":* " + diseaseEvent.toString());
    }
}
