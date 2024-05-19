package io.lumeer.core.model.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Attribute;

public class AiSuggestDataTypeResponse {

    private Attribute attribute;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiSuggestDataTypeResponse(
            @JsonProperty("attribute") Attribute attribute,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.attribute = attribute;
        this.error = error;
        this.errorMessage = errorMessage;
    }


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AiChechDataTypeResponse{" +
                "attribute=" + attribute +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
