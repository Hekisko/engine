package io.lumeer.core.model.types.color;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;

public class ColorType extends AbstractType {

    private ColorExample exampleValue;

    public ColorType() {
    }

    public ColorType(ColorExample exampleValue) {
        super(EAiType.COLOR);
        this.exampleValue = exampleValue;
    }

    public ColorExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(ColorExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    @Override
    public Object getConstraints() {
        return new HashMap<>();
    }
    @Override
    public String toString() {
        return "ColorType{" +
                "exampleValue=" + exampleValue +
                '}';
    }
}
