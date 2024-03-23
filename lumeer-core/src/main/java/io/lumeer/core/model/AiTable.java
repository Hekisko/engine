package io.lumeer.core.model;


import java.util.List;

public class AiTable {

    private String color;
    private String name;
    private List<AiColumn> columns;

    public AiTable() {
    }

    public AiTable(String color, String name, List<AiColumn> columns) {
        this.color = color;
        this.name = name;
        this.columns = columns;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AiColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<AiColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "AiTable{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
