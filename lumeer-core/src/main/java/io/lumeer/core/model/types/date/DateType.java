package io.lumeer.core.model.types.date;


import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

public class DateType extends AbstractType {

    private DateExample exampleValue;
    private DateTypeDetail typeDetail;

    public DateType() {
        this(new DateExample(), new DateTypeDetail());
    }

    public DateType(DateExample exampleValue, DateTypeDetail typeDetail) {
        super(EAiType.DATE);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public DateExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(DateExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public DateTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(DateTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }
    @Override
    public String toString() {
        return "DateType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
