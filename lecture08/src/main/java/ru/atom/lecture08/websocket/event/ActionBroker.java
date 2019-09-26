package ru.atom.lecture08.websocket.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionBroker {
    private static final Logger log = LoggerFactory.getLogger(ActionBroker.class);
    private final ArrayList<Listener> listeners = new ArrayList<>();

    public void subscribe(List<Action> triggerActions, Listener listener) {
        listener.setTriggerActions(triggerActions);
        listeners.add(listener);
        log.info("Listener added {}", listener);
    }

    public void unsubscribe(Listener listener) {
        listeners.remove(listener);
    }

    public void addEvent(Action action) {
        for (Listener listener: listeners) {
            if (listener.getTriggerActions().contains(action)) {
                listener.callback();
            }
        }
    }
}
