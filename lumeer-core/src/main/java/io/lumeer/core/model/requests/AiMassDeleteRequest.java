package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Attribute;

import java.util.Arrays;

public class AiMassDeleteRequest {

    private String[] data;
    private String deleteDescription;

    @JsonCreator
    public AiMassDeleteRequest(@JsonProperty("data") String[] data,
                               @JsonProperty("deleteDescription") String deleteDescription) {
        this.data = data;
        this.deleteDescription = deleteDescription;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getDeleteDescription() {
        return deleteDescription;
    }

    public void setDeleteDescription(String deleteDescription) {
        this.deleteDescription = deleteDescription;
    }

    @Override
    public String toString() {
        return "AiMassDeleteRequest{" +
                "data=" + Arrays.toString(data) +
                ", deleteDescription='" + deleteDescription + '\'' +
                '}';
    }
}
