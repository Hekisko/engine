package io.lumeer.core.model.types.address;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;
import java.util.Map;

public class AddressType extends AbstractType {

    private AddressExample exampleValue;
    private AddressTypeDetail typeDetail;

    public AddressType() {
        this(new AddressExample(), new AddressTypeDetail());
    }

    public AddressType(AddressExample exampleValue, AddressTypeDetail typeDetail) {
        super(EAiType.ADDRESS);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public AddressExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(AddressExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public AddressTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(AddressTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }
    @Override
    public String toString() {
        return "AddressType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
