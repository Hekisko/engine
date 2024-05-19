package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Attribute;

import java.util.Arrays;

public class AiCheckDataRequest {

    private String[] data;

    @JsonCreator
    public AiCheckDataRequest(@JsonProperty("dataToBeChecked") String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AiCheckDataTypeRequest{" +
                "dataToBeChecked=" + Arrays.toString(data) +
                '}';
    }
}
