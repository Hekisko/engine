package io.lumeer.core.model;

import io.lumeer.core.model.types.AbstractType;

public class AiColumn {

    private String name;
    private AbstractType type;

    public AiColumn() {
    }

    public AiColumn(String name, AbstractType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractType getType() {
        return type;
    }

    public void setType(AbstractType type) {
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
