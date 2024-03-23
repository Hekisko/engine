package io.lumeer.core.model.types.duration;

public class DurationExample {

    private long duration;

    public DurationExample() {
    }

    public DurationExample(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DurationExample{" +
                "duration=" + duration +
                '}';
    }
}
