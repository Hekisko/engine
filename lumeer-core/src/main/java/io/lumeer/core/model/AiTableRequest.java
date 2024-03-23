package io.lumeer.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AiTableRequest {

    private String tablesDescription;

    @JsonCreator
    public AiTableRequest(@JsonProperty("tablesDescription") String tablesDescription) {
        this.tablesDescription = tablesDescription;
    }

    public String getTablesDescription() {
        return tablesDescription;
    }

    public void setTablesDescription(String tablesDescription) {
        this.tablesDescription = tablesDescription;
    }

    @Override
    public String toString() {
        return "AiTableRequest{" +
                "tablesDescription='" + tablesDescription + '\'' +
                '}';
    }
}
