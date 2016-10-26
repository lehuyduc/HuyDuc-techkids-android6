package controllers;

import models.GameObject;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public interface Subscriber {
    void onEvent(EventType eventType, SingleController sender);
    GameObject getSubscriberObject();
}
