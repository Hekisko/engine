package io.lumeer.core.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.types.AbstractType;


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

    public AbstractType getJavaType(ObjectMapper objectMapper) throws JsonProcessingException {
        return getType().getType(this, objectMapper);
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
