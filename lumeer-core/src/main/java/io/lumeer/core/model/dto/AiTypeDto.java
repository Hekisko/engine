package io.lumeer.core.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.lumeer.core.model.enums.EAiType;

public class AiTypeDto {

    private EAiType type;
    private JsonNode exampleValue;
    private JsonNode typeDetail;

    public AiTypeDto() {
    }

    public AiTypeDto(EAiType type, JsonNode exampleValue, JsonNode typeDetail) {
        this.type = type;
        this.exampleValue = exampleValue;
        this.typeDetail = typeDetail;
    }

    public EAiType getType() {
        return type;
    }

    public void setType(EAiType type) {
        this.type = type;
    }

    public JsonNode getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(JsonNode exampleValue) {
        this.exampleValue = exampleValue;
    }

    public JsonNode getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(JsonNode typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Override
    public String toString() {
        return "AiType{" +
                "type=" + type +
                ", exampleValue=" + exampleValue +
                ", typeDetail=" + typeDetail +
                '}';
    }
}
