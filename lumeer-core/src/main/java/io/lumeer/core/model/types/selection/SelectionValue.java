package io.lumeer.core.model.types.selection;

import java.util.HashMap;
import java.util.Map;

public class SelectionValue {

    private String name;
    private String color;

    public SelectionValue() {
    }

    public SelectionValue(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("value", name);
        resultMap.put("background", color);

        return resultMap;
    }

    @Override
    public String toString() {
        return "SelectionValue{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
