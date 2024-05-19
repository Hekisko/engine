package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Attribute;

import java.util.Arrays;

public class AiSuggestDataTypeRequest {

    private String[] data;
    private Attribute attribute;

    @JsonCreator
    public AiSuggestDataTypeRequest(@JsonProperty("dataToBeChecked") String[] data,
                                    @JsonProperty("attribute") Attribute attribute) {
        this.data = data;
        this.attribute = attribute;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AiCheckDataTypeRequest{" +
                "dataToBeChecked=" + Arrays.toString(data) +
                ", attribute=" + attribute +
                '}';
    }
}
