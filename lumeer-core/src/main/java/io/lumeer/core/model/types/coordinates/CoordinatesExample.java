package io.lumeer.core.model.types.coordinates;


public class CoordinatesExample {

    private String first;
    private String second;

    public CoordinatesExample() {
    }

    public CoordinatesExample(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "CoordinatesExample{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
