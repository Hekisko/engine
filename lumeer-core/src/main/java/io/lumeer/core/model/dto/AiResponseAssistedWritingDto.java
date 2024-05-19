package io.lumeer.core.model.dto;


public class AiResponseAssistedWritingDto {

    private boolean error;
    private String errorMessage;
    private String text;

    public AiResponseAssistedWritingDto() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AiResponseAssistedWritingDto{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
