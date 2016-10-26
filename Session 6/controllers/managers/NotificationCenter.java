package controllers.managers;

import controllers.EventType;
import controllers.SingleController;
import controllers.Subscriber;

import java.util.Vector;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public class NotificationCenter {

    private Vector<Subscriber> subscribers;

    public NotificationCenter() {
        subscribers = new Vector<>();
    }

    public void register(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unregister(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void onEvent(EventType eventType, SingleController sender) {
        for (Subscriber subscriber : subscribers)
            subscriber.onEvent(eventType,sender);
    }

    public static final NotificationCenter instance = new NotificationCenter();
}
