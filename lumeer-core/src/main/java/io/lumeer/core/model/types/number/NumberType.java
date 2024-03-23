package io.lumeer.core.model.types.number;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

public class NumberType extends AbstractType {

    private NumberExample exampleValue;
    private NumberTypeDetail typeDetail;

    public NumberType() {
    }

    public NumberType(NumberExample exampleValue, NumberTypeDetail typeDetail) {
        super(EAiType.NUMBER);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public NumberExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(NumberExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public NumberTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(NumberTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }

    @Override
    public String toString() {
        return "NumberType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
