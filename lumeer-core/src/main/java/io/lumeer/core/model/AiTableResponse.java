package io.lumeer.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.api.model.Collection;

import java.util.List;

public class AiTableResponse {

    private List<Collection> tables;
    private boolean error;
    private String errorMessage;

    @JsonCreator
    public AiTableResponse(
            @JsonProperty("tables") List<Collection> tables,
            @JsonProperty("error") boolean error,
            @JsonProperty("errorMessage") String errorMessage) {
        this.tables = tables;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public List<Collection> getTables() {
        return tables;
    }

    public void setTables(List<Collection> tables) {
        this.tables = tables;
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

    @Override
    public String toString() {
        return "AiTableResponse{" +
                "tables=" + tables +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
