package io.lumeer.core.model.types.coordinates;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

public class CoordinatesType extends AbstractType {

    private CoordinatesExample exampleValue;
    private CoordinatesTypeDetail typeDetail;

    public CoordinatesType() {
    }

    public CoordinatesType(CoordinatesExample exampleValue, CoordinatesTypeDetail typeDetail) {
        super(EAiType.COORDINATES);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public CoordinatesExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(CoordinatesExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public CoordinatesTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(CoordinatesTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }

    @Override
    public String toString() {
        return "CoordinatesType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
