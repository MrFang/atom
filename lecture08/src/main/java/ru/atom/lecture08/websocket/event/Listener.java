package ru.atom.lecture08.websocket.event;

import java.util.ArrayList;
import java.util.List;

public interface Listener {
    ArrayList<Action> triggerActions = new ArrayList<>();

    void callback();

    default Listener setTriggerActions(List<Action> actions) {
        triggerActions.clear();
        triggerActions.addAll(actions);

        return this;
    }

    default List<Action> getTriggerActions() {
        return triggerActions;
    }
}
