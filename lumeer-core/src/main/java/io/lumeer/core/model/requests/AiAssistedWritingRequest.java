package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.lumeer.core.model.enums.ETypeAssistedWriting;


public class AiAssistedWritingRequest {

    private String inputString;
    private ETypeAssistedWriting eTypeAssistedWriting;

    @JsonCreator
    public AiAssistedWritingRequest(@JsonProperty("inputString") String inputString,
                                    @JsonProperty("eTypeAssistedWriting") ETypeAssistedWriting eTypeAssistedWriting) {
        this.inputString = inputString;
        this.eTypeAssistedWriting = eTypeAssistedWriting;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public ETypeAssistedWriting geteTypeAssistedWriting() {
        return eTypeAssistedWriting;
    }

    public void seteTypeAssistedWriting(ETypeAssistedWriting eTypeAssistedWriting) {
        this.eTypeAssistedWriting = eTypeAssistedWriting;
    }

    @Override
    public String toString() {
        return "AiAssistedWritingRequest{" +
                "inputString='" + inputString + '\'' +
                ", eTypeAssistedWriting=" + eTypeAssistedWriting +
                '}';
    }
}
