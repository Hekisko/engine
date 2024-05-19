package io.lumeer.core.model.types.number;

public class NumberExample {

    private Double number;

    public NumberExample() {
        this(1.);
    }

    public NumberExample(Double number) {
        this.number = number;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "NumberExample{" +
                "number=" + number +
                '}';
    }
}
