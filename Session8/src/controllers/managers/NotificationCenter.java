package controllers.managers;

import controllers.EventType;
import controllers.SingleController;
import controllers.Subscriber;
import models.GameObject;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public class NotificationCenter {

    private Vector<Subscriber> subscribers;

    public NotificationCenter() {
        subscribers = new Vector<>();
    }

    public void add(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void remove(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void remove() {
        Iterator<Subscriber> it = subscribers.iterator();
        while (it.hasNext()) {
            Subscriber subscriber = it.next();
            GameObject gameObject = subscriber.getSubscriberObject();
            if (gameObject.deleteNow()) it.remove();
        }
    }

    public void onEvent(EventType eventType, SingleController sender) {
        for (Subscriber subscriber : subscribers)
            subscriber.onEvent(eventType,sender);
    }

    public void run() {
        remove();
    }

    public static final NotificationCenter instance = new NotificationCenter();
}
