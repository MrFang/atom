package ru.atom.thread.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author apomosov
 * @since 15.03.17
 */
public class EventProcessor {
    public static void produceEvents(List<EventProducer> eventProducers) {
        List<Thread> listOfThread = new ArrayList<Thread>();

        EventHandler eventHandler = new EventHandler();
        eventHandler.start();

        for (EventProducer eventProducer : eventProducers) {
            Thread newThread = new Thread(eventProducer);
            listOfThread.add(newThread);
            newThread.start();
        }

        for (Thread thread : listOfThread) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {
                //
            }
        }

        eventHandler.interrupt();

        try {
            eventHandler.join();
        } catch (InterruptedException ignore) {
            //
        }
    }

    public static long countTotalNumberOfGoodEvents() {
        return EventHandler.countOfGoodEvents;
    }

    public static long countTotalNumberOfBadEvents() {
        return EventHandler.countOfBadEvents;
    }
}
