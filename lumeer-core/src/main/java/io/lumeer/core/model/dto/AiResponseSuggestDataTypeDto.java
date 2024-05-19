package io.lumeer.core.model.dto;


import java.util.Arrays;

public class AiResponseSuggestDataTypeDto {

    private boolean error;
    private String errorMessage;
    private AiTypeDto type;

    public AiResponseSuggestDataTypeDto() {
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

    public AiTypeDto getType() {
        return type;
    }

    public void setType(AiTypeDto type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AiResponseSuggestDataTypeDto{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", type=" + type +
                '}';
    }
}
