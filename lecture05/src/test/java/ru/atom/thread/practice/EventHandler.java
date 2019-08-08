package ru.atom.thread.practice;

import java.util.concurrent.BlockingQueue;

public class EventHandler extends Thread {
    public static long countOfGoodEvents = 0;
    public static long countOfBadEvents = 0;

    @Override
    public void run() {
        BlockingQueue<Event> eventQueue = EventQueue.getInstance();

        while (!(Thread.interrupted() && eventQueue.isEmpty())) {
            try {
                Event event = eventQueue.take();

                if (event.getEventType() == Event.EventType.GOOD) {
                    countOfGoodEvents++;
                }

                if (event.getEventType() == Event.EventType.BAD) {
                    countOfBadEvents++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
