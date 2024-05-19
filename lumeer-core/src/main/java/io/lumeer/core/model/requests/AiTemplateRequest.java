package io.lumeer.core.model.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class AiTemplateRequest {

    private String projectDescription;

    @JsonCreator
    public AiTemplateRequest(@JsonProperty("projectDescription") String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public String toString() {
        return "AiTemplateRequest{" +
                "projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
