package io.lumeer.core.model.dto;


import java.util.Arrays;

public class AiResponseTemplateDto {

    private boolean error;
    private String errorMessage;
    private String[] result;

    public AiResponseTemplateDto() {
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

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AiResponseTemplateDto{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
