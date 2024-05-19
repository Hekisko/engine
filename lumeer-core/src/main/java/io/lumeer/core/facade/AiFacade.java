/*
 * Lumeer: Modern Data Definition and Processing Platform
 *
 * Copyright (C) since 2017 Lumeer.io, s.r.o. and/or its affiliates.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.lumeer.core.facade;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lumeer.api.model.*;
import io.lumeer.api.model.Collection;
import io.lumeer.core.adapter.AiAdapter;
import io.lumeer.core.model.*;
import io.lumeer.core.model.dto.*;
import io.lumeer.core.model.enums.ETypeAssistedWriting;
import io.lumeer.core.model.requests.AiAssistedWritingRequest;
import io.lumeer.core.model.requests.AiMassDeleteRequest;
import io.lumeer.core.model.requests.AiSuggestDataTypeRequest;
import io.lumeer.core.model.requests.AiTableRequest;
import io.lumeer.core.model.responses.*;
import io.lumeer.core.model.types.AbstractType;
import io.lumeer.core.util.Utils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

@RequestScoped
public class AiFacade extends AbstractFacade {

    private final String CANNOT_DESERIALIZE = "Bad format of AI response";
    private AiAdapter aiAdapter;

    @PostConstruct
    public void init() {
        aiAdapter = new AiAdapter(System.getenv("OPENAI_API_KEY"));
    }

    @NotNull
    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = Utils.createJacksonObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        return objectMapper;
    }

    public AiSuggestDataTypeResponse suggestDataType(AiSuggestDataTypeRequest request) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            List<String> presentData = Arrays.stream(request.getData()).map(elem -> "\\\"" + elem + "\\\"").toList();

            String aiResponseStr = aiAdapter.suggestDataType(getEscapedValue(presentData.toString()));
            AiResponseSuggestDataTypeDto responseDto =
                    getResponseSuggestDataType(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageSuggestDataType(responseDto.getErrorMessage());
            }

            if (responseDto.getType() == null) {
                return returnErrorMessageSuggestDataType(CANNOT_DESERIALIZE);
            }

            AbstractType abstractType = responseDto.getType().getJavaType(objectMapper);
            request.getAttribute().setConstraint(getConstraint(abstractType));

            return new AiSuggestDataTypeResponse(
                    request.getAttribute(),
                    false,
                    null
            );

        } catch (JsonProcessingException e) {
            return returnErrorMessageSuggestDataType(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageSuggestDataType(e.getMessage());
        }
    }

    public AiCheckDataResponse checkData(String[] data) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            List<String> dataToBeChecked = Arrays.stream(data).map(elem -> "\\\"" + elem + "\\\"").toList();

            String aiResponseStr = aiAdapter.checkData(getEscapedValue(dataToBeChecked.toString()));
            AiResponseCheckDataDto responseDto = getResponseCheckData(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageCheckData(responseDto.getErrorMessage());
            }

            if (responseDto.getValues() == null) {
                return returnErrorMessageCheckData(CANNOT_DESERIALIZE);
            }

            return new AiCheckDataResponse(
                    responseDto.getValues(),
                    false,
                    null
            );

        } catch (JsonProcessingException e) {
            return returnErrorMessageCheckData(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageCheckData(e.getMessage());
        }
    }

    public AiAssistedWritingResponse assistedWriting(AiAssistedWritingRequest request) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            String aiResponseStr;
            if (request.geteTypeAssistedWriting() == ETypeAssistedWriting.CONTRACT) {
                aiResponseStr = aiAdapter.assistedWritingContract(getEscapedValue(request.getInputString()));
            } else {
                aiResponseStr = aiAdapter.assistedWritingExpand(getEscapedValue(request.getInputString()));
            }
            AiResponseAssistedWritingDto responseDto =
                    getResponseAssistedWriting(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageAssistedWriting(responseDto.getErrorMessage());
            }

            if (responseDto.getText() == null) {
                return returnErrorMessageAssistedWriting(CANNOT_DESERIALIZE);
            }

            return new AiAssistedWritingResponse(
                    responseDto.getText(),
                    false,
                    null
            );

        } catch (JsonProcessingException e) {
            return returnErrorMessageAssistedWriting(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageAssistedWriting(e.getMessage());
        }
    }

    public AiTemplateResponse getBestTemplates(String projectDescription) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            String aiResponseStr = aiAdapter.template(getEscapedValue(projectDescription));
            AiResponseTemplateDto responseDto = getResponseTemplate(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageTemplate(responseDto.getErrorMessage());
            }

            if (responseDto.getResult() == null) {
                return returnErrorMessageTemplate(CANNOT_DESERIALIZE);
            }

            return new AiTemplateResponse(
                    responseDto.getResult(),
                    false,
                    null
            );

        } catch (JsonProcessingException e) {
            return returnErrorMessageTemplate(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageTemplate(e.getMessage());
        }
    }

    public AiMassDeleteResponse massDelete(AiMassDeleteRequest aiMassDeleteRequest) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            List<String> dataToBeChecked = Arrays.stream(aiMassDeleteRequest.getData()).map(elem -> elem.replaceAll("\n", "\\n")).toList();
            String joinedData = String.join("\n", dataToBeChecked);

            String aiResponseStr = aiAdapter.massDelete(getEscapedValue(aiMassDeleteRequest.getDeleteDescription()), getEscapedValue(joinedData));
            AiResponseMassDeleteDto responseDto = getResponseMassDelete(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageMassDelete(responseDto.getErrorMessage());
            }

            if (responseDto.getValues() == null) {
                return returnErrorMessageMassDelete(CANNOT_DESERIALIZE);
            }

            return new AiMassDeleteResponse(
                    responseDto.getValues(),
                    false,
                    null
            );

        } catch (JsonProcessingException e) {
            return returnErrorMessageMassDelete(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageMassDelete(e.getMessage());
        }
    }

    public AiTableResponse createTablesWithAi(AiTableRequest aiTableRequest) {
        ObjectMapper objectMapper = getObjectMapper();
        try {

            String aiResponseStr = aiAdapter.createTableWithAi(
                    getEscapedValue(aiTableRequest.getTablesDescription()),
                    aiTableRequest.getOldAiTablesGeneratedStr() == null ? "" : getEscapedValue(aiTableRequest.getOldAiTablesGeneratedStr()));
            AiResponseTablesDto responseDto = getResponseTable(objectMapper.readTree(aiResponseStr), objectMapper);

            if (responseDto.isError()) {
                return returnErrorMessageTables(responseDto.getErrorMessage());
            }

            if (responseDto.getTables() == null) {
                return returnErrorMessageTables(CANNOT_DESERIALIZE);
            }

            List<AiTable> AiTables = mapTableFromDto(responseDto, objectMapper);
            List<Collection> tables = mapAiTableToCollection(AiTables);


            return new AiTableResponse(
                    tables,
                    getResponseAsText(objectMapper.readTree(aiResponseStr)),
                    false,
                    null
            );
        } catch (JsonProcessingException e) {
            return returnErrorMessageTables(CANNOT_DESERIALIZE);
        } catch (NullPointerException | IllegalArgumentException | IllegalStateException e) {
            return returnErrorMessageTables(e.getMessage());
        }
    }

    private List<Collection> mapAiTableToCollection(List<AiTable> aiTables) {
        Permissions permissions = new Permissions();
        permissions.updateUserPermissions(Permission.buildWithRoles(getCurrentUserId(), Project.ROLES));

        return aiTables.stream().map(aiTable -> new Collection(
                UUID.randomUUID().toString(),
                aiTable.getName(),
                "fa-image",
                aiTable.getColor(),
                "",
                null,
                permissions,
                getAttributes(aiTable.getColumns()),
                null,
                null,
                null
        )).collect(Collectors.toList());
    }

    private Set<Attribute> getAttributes(List<AiColumn> columns) {
        Set<Attribute> mappedAttributed = new LinkedHashSet<>();
        for (AiColumn column : columns) {
            mappedAttributed.add(createAttribute(column.getName(), column.getType()));
        }
        return mappedAttributed;
    }

    private Attribute createAttribute(String name, AbstractType type) {
        return new Attribute(
                UUID.randomUUID().toString(),
                name,
                "",
                getConstraint(type),
                null,
                null,
                null,
                0,
                null
        );
    }

    private Constraint getConstraint(AbstractType type) {
        return new Constraint(
                type.getType().getLumeerType(),
                type.getConstraints()
        );
    }

    private List<AiTable> mapTableFromDto(AiResponseTablesDto responseTablesDto,
                                          ObjectMapper objectMapper) throws IllegalStateException {

        List<AiTable> result = new ArrayList<>();

        for (AiTableDto tableDto : responseTablesDto.getTables()) {

            result.add(new AiTable(
                    tableDto.getColor(),
                    tableDto.getName(),
                    tableDto.getColumns().stream().map(aiColumnDto -> {
                        try {
                            return mapColumnFromDto(aiColumnDto, objectMapper);
                        } catch (JsonProcessingException e) {
                            throw new IllegalStateException(e);
                        }
                    }).toList()
            ));
        }
        return result;
    }

    private AiColumn mapColumnFromDto(AiColumnDto aiColumnDto,
                                      ObjectMapper objectMapper) throws JsonProcessingException {

        return new AiColumn(
                aiColumnDto.getName(),
                aiColumnDto.getType().getJavaType(objectMapper)
        );
    }

    private String getResponseAsText(JsonNode root) {

        String resultStr = root.get("choices").get(0).get("message").get("content").asText();
        if (!resultStr.startsWith("{")) {
            int startingIndex = resultStr.indexOf("{");
            if (startingIndex == -1) {
                throw new IllegalArgumentException(CANNOT_DESERIALIZE);
            }
            resultStr = resultStr.substring(startingIndex);
            int endingIndex = resultStr.lastIndexOf("}");
            if (endingIndex == -1) {
                throw new IllegalArgumentException(CANNOT_DESERIALIZE);
            }
            resultStr = resultStr.substring(0, endingIndex + 1);
            return resultStr;
        }

        return resultStr;
    }

    private String getEscapedValue(String value) throws JsonProcessingException {
        ObjectMapper objectMapper = getObjectMapper();
        String escapedData = objectMapper.writeValueAsString(value);
        return escapedData.substring(1, escapedData.length() - 1);
    }

    private AiResponseTablesDto getResponseTable(JsonNode root,
                                                 ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseTablesDto.class);
    }

    private AiResponseAssistedWritingDto getResponseAssistedWriting(JsonNode root,
                                                                    ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseAssistedWritingDto.class);
    }

    private AiResponseTemplateDto getResponseTemplate(JsonNode root,
                                                      ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseTemplateDto.class);
    }

    private AiResponseMassDeleteDto getResponseMassDelete(JsonNode root,
                                                          ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseMassDeleteDto.class);
    }

    private AiResponseCheckDataDto getResponseCheckData(JsonNode root,
                                                        ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseCheckDataDto.class);
    }

    private AiResponseSuggestDataTypeDto getResponseSuggestDataType(JsonNode root,
                                                                    ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(getResponseAsText(root), AiResponseSuggestDataTypeDto.class);
    }

    private AiTableResponse returnErrorMessageTables(String error) {
        return new AiTableResponse(
                null,
                null,
                true,
                error
        );
    }

    private AiAssistedWritingResponse returnErrorMessageAssistedWriting(String error) {
        return new AiAssistedWritingResponse(
                null,
                true,
                error
        );
    }

    private AiTemplateResponse returnErrorMessageTemplate(String error) {
        return new AiTemplateResponse(
                null,
                true,
                error
        );
    }

    private AiMassDeleteResponse returnErrorMessageMassDelete(String error) {
        return new AiMassDeleteResponse(
                null,
                true,
                error
        );
    }

    private AiCheckDataResponse returnErrorMessageCheckData(String error) {
        return new AiCheckDataResponse(
                null,
                true,
                error
        );
    }

    private AiSuggestDataTypeResponse returnErrorMessageSuggestDataType(String error) {
        return new AiSuggestDataTypeResponse(
                null,
                true,
                error
        );
    }
}
