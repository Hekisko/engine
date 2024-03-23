package io.lumeer.core.model.dto;

import java.util.List;

public class AiResponseTablesDto {

    private boolean error;
    private String errorMessage;
    private List<AiTableDto> tables;

    public AiResponseTablesDto() {
    }

    public AiResponseTablesDto(boolean error, String errorMessage, List<AiTableDto> tables) {
        this.error = error;
        this.errorMessage = errorMessage;
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

    public List<AiTableDto> getTables() {
        return tables;
    }

    public void setTables(List<AiTableDto> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "AiResponseTables{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", tables=" + tables +
                '}';
    }
}
