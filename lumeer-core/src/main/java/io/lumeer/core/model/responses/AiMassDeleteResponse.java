package io.lumeer.core.model.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class AiMassDeleteResponse {

    private String[] idsToBeDeleted;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiMassDeleteResponse(
            @JsonProperty("idsToBeDeleted") String[] idsToBeDeleted,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.idsToBeDeleted = idsToBeDeleted;
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

    public String[] getIdsToBeDeleted() {
        return idsToBeDeleted;
    }

    public void setIdsToBeDeleted(String[] idsToBeDeleted) {
        this.idsToBeDeleted = idsToBeDeleted;
    }

    @Override
    public String toString() {
        return "AiMassDeleteResponse{" +
                "idsToBeDeleted=" + Arrays.toString(idsToBeDeleted) +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
