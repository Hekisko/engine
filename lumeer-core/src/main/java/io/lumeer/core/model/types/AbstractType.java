package io.lumeer.core.model.types;


import io.lumeer.core.model.enums.EAiType;

public abstract class AbstractType {

    private EAiType type;

    public AbstractType(EAiType type) {
        this.type = type;
    }

    public AbstractType() {}

    public EAiType getType() {
        return type;
    }

    public void setType(EAiType type) {
        this.type = type;
    }

    public abstract Object getConstraints();
}
