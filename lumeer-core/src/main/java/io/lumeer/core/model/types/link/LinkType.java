package io.lumeer.core.model.types.link;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;

public class LinkType extends AbstractType {

    private LinkExample exampleValue;

    public LinkType() {
    }

    public LinkType(LinkExample exampleValue) {
        super(EAiType.LINK);
        this.exampleValue = exampleValue;
    }

    public LinkExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(LinkExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    @Override
    public Object getConstraints() {
        return new HashMap<>();
    }

    @Override
    public String toString() {
        return "LinkType{" +
                "exampleValue=" + exampleValue +
                '}';
    }
}
