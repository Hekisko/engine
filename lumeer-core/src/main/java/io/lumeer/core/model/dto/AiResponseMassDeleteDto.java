package io.lumeer.core.model.dto;


import java.util.Arrays;

public class AiResponseMassDeleteDto {

    private boolean error;
    private String errorMessage;
    private String[] values;

    public AiResponseMassDeleteDto() {
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
        return "AiResponseTemplateDto{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
