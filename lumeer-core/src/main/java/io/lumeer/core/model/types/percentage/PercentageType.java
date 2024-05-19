package io.lumeer.core.model.types.percentage;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.enums.EPercentageFormat;
import io.lumeer.core.model.types.AbstractType;

public class PercentageType extends AbstractType {

    private PercentageExample exampleValue;
    private PercentageTypeDetail typeDetail;

    public PercentageType() {
        this(new PercentageExample(), new PercentageTypeDetail());
    }

    public PercentageType(PercentageExample exampleValue, PercentageTypeDetail typeDetail) {
        super(EAiType.PERCENTAGE);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public PercentageExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(PercentageExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public PercentageTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(PercentageTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }

    @Override
    public String toString() {
        return "PercentageType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
