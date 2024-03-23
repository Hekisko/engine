package io.lumeer.core.model.dto;

import java.util.List;

public class AiTableDto {

    private String color;
    private String name;
    private List<AiColumnDto> columns;

    public AiTableDto() {
    }

    public AiTableDto(String color, String name, List<AiColumnDto> columns) {
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

    public List<AiColumnDto> getColumns() {
        return columns;
    }

    public void setColumns(List<AiColumnDto> columns) {
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
