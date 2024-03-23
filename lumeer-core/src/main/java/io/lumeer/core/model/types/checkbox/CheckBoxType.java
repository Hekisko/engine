package io.lumeer.core.model.types.checkbox;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;

public class CheckBoxType extends AbstractType {

    private CheckBoxExample exampleValue;

    public CheckBoxType() {
    }

    public CheckBoxType(CheckBoxExample exampleValue) {
        super(EAiType.CHECK_BOX);
        this.exampleValue = exampleValue;
    }

    public CheckBoxExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(CheckBoxExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    @Override
    public Object getConstraints() {
        return new HashMap<>();
    }

    @Override
    public String toString() {
        return "CheckBoxType{" +
                "exampleValue=" + exampleValue +
                '}';
    }
}
