package io.lumeer.core.model.types.color;

public class ColorExample {

    private String color;

    public ColorExample() {
        this("#ff0000");
    }

    public ColorExample(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ColorExample{" +
                "color='" + color + '\'' +
                '}';
    }
}
