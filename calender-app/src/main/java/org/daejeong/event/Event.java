package org.daejeong.event;

public interface Event {
    void print();

    boolean support(EventType type);
}
