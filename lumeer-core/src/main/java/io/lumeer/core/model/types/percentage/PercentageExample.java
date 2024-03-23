package io.lumeer.core.model.types.percentage;

public class PercentageExample {

    private Double percentage;

    public PercentageExample() {
    }

    public PercentageExample(Double percentage) {
        this.percentage = percentage;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "PercentageExample{" +
                "percentage=" + percentage +
                '}';
    }
}
