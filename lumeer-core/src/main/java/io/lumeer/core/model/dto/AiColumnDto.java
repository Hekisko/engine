package io.lumeer.core.model.dto;

public class AiColumnDto {

    private String name;
    private AiTypeDto type;

    public AiColumnDto() {
    }

    public AiColumnDto(String name, AiTypeDto type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AiTypeDto getType() {
        return type;
    }

    public void setType(AiTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AiColumn{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
