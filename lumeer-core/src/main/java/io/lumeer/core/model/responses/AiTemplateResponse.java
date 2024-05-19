package io.lumeer.core.model.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;


public class AiTemplateResponse {

    private String[] bestMatchTemplates;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiTemplateResponse(
            @JsonProperty("bestMatchTemplates") String[] bestMatchTemplates,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.bestMatchTemplates = bestMatchTemplates;
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

    public String[] getBestMatchTemplates() {
        return bestMatchTemplates;
    }

    public void setBestMatchTemplates(String[] bestMatchTemplates) {
        this.bestMatchTemplates = bestMatchTemplates;
    }

    @Override
    public String toString() {
        return "AiTemplateResponse{" +
                "bestMatchTemplates=" + Arrays.toString(bestMatchTemplates) +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
