package io.lumeer.core.model.types.selection;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

public class SelectionType extends AbstractType {

    private SelectionExample exampleValue;
    private SelectionTypeDetail typeDetail;

    public SelectionType() {
    }

    public SelectionType(SelectionExample exampleValue, SelectionTypeDetail typeDetail) {
        super(EAiType.SELECTION);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public SelectionExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(SelectionExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public SelectionTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(SelectionTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }
    @Override
    public String toString() {
        return "SelectionType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
