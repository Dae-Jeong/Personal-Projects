package org.daejeong.event;

import org.daejeong.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class NoDisturbance extends AbstractEvent {

    public NoDisturbance(int id, String title, ZonedDateTime startAt, ZonedDateTime endAt) {
        super(id, title, startAt, endAt);
    }

    @Override
    protected void update(AbstractAuditableEvent update) {

    }

    @Override
    public void print() {
        System.out.printf("[방해 금지] %s", getTitle());

    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.NO_DISTURBANCE;

    }
}
