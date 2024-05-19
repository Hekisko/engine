package io.lumeer.core.model.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Attribute;

import java.util.Arrays;

public class AiCheckDataResponse {

    private String[] invalidData;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiCheckDataResponse(
            @JsonProperty("invalidData") String[] invalidData,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.invalidData = invalidData;
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

    public String[] getInvalidData() {
        return invalidData;
    }

    public void setInvalidData(String[] invalidData) {
        this.invalidData = invalidData;
    }

    @Override
    public String toString() {
        return "AiCheckDataResponse{" +
                "invalidData=" + Arrays.toString(invalidData) +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
