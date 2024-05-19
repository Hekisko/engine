package io.lumeer.core.model.dto;


import java.util.Arrays;

public class AiResponseCheckDataDto {

    private boolean error;
    private String errorMessage;
    private String[] values;

    public AiResponseCheckDataDto() {
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

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "AiResponseCheckDataDto{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
