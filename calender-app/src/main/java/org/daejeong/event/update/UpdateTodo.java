package org.daejeong.event.update;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdateTodo extends AbstractAuditableEvent {

    private String description;

    public UpdateTodo(String title, ZonedDateTime startAt, ZonedDateTime endAt, String description) {
        super(title, startAt, endAt);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
