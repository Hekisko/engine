package io.lumeer.core.model.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AiAssistedWritingResponse {

    private String generatedString;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiAssistedWritingResponse(
            @JsonProperty("generatedString") String generatedString,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.generatedString = generatedString;
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

    public String getGeneratedString() {
        return generatedString;
    }

    public void setGeneratedString(String generatedString) {
        this.generatedString = generatedString;
    }

    @Override
    public String toString() {
        return "AiAssistedWritingResponse{" +
                "generatedString='" + generatedString + '\'' +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
