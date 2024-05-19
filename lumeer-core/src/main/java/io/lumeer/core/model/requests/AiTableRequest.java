package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AiTableRequest {

    private String tablesDescription;
    private String oldAiTablesGeneratedStr;

    @JsonCreator
    public AiTableRequest(@JsonProperty("tablesDescription") String tablesDescription,
                          @JsonProperty("tldAiTablesGeneratedStr") String oldAiTablesGeneratedStr) {
        this.tablesDescription = tablesDescription;
        this.oldAiTablesGeneratedStr = oldAiTablesGeneratedStr;
    }

    public String getTablesDescription() {
        return tablesDescription;
    }

    public void setTablesDescription(String tablesDescription) {
        this.tablesDescription = tablesDescription;
    }

    public String getOldAiTablesGeneratedStr() {
        return oldAiTablesGeneratedStr;
    }

    public void setOldAiTablesGeneratedStr(String oldAiTablesGeneratedStr) {
        this.oldAiTablesGeneratedStr = oldAiTablesGeneratedStr;
    }

    @Override
    public String toString() {
        return "AiTableRequest{" +
                "tablesDescription='" + tablesDescription + '\'' +
                ", oldAiTablesGeneratedStr='" + oldAiTablesGeneratedStr + '\'' +
                '}';
    }
}
