package org.elasticsearch.common.component;

public class Lifecycle {
    public enum State {
        INITIALIZED,
        STOPPED,
        STARTED,
        CLOSED
    }

    private volatile State state = State.INITIALIZED;
    public boolean canMoveToStarted() {
        State localState = this.state;
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            return true;
        }
        if (localState == State.STARTED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new IllegalArgumentException("Can't move to started when closed");
        }
        throw new IllegalStateException("Can't move to startend with unknow state");
    }

    public boolean moveToStarted() {
        State localState = this.state;
        if (localState == State.INITIALIZED || localState == State.STOPPED) {
            state = State.STARTED;
            return true;
        }
        if (localState == State.STARTED) {
            return false;
        }
        if (localState == State.CLOSED) {
            throw new IllegalArgumentException("Can't move to started when closed");
        }
        throw new IllegalStateException("Can't move to startend with unknow state");
    }
}
