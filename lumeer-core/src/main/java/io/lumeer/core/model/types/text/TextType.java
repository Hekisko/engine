package io.lumeer.core.model.types.text;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;;

public class TextType extends AbstractType {

    private TextExample exampleValue;
    private TextTypeDetail typeDetail;

    public TextType() {
        this(new TextExample(), new TextTypeDetail());
    }

    public TextType(TextExample exampleValue, TextTypeDetail typeDetail) {
        super(EAiType.TEXT);
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public TextExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(TextExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    public TextTypeDetail getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(TextTypeDetail typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public Object getConstraints() {
        return typeDetail.getConstraints();
    }

    @Override
    public String toString() {
        return "TextType{" +
                "exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
