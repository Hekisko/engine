package io.lumeer.core.model.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lumeer.api.model.ConstraintType;
import io.lumeer.core.model.dto.AiTypeDto;
import io.lumeer.core.model.types.AbstractType;
import io.lumeer.core.model.types.address.AddressExample;
import io.lumeer.core.model.types.address.AddressType;
import io.lumeer.core.model.types.address.AddressTypeDetail;
import io.lumeer.core.model.types.checkbox.CheckBoxExample;
import io.lumeer.core.model.types.checkbox.CheckBoxType;
import io.lumeer.core.model.types.color.ColorExample;
import io.lumeer.core.model.types.color.ColorType;
import io.lumeer.core.model.types.coordinates.CoordinatesExample;
import io.lumeer.core.model.types.coordinates.CoordinatesType;
import io.lumeer.core.model.types.coordinates.CoordinatesTypeDetail;
import io.lumeer.core.model.types.date.DateExample;
import io.lumeer.core.model.types.date.DateType;
import io.lumeer.core.model.types.date.DateTypeDetail;
import io.lumeer.core.model.types.duration.DurationExample;
import io.lumeer.core.model.types.duration.DurationType;
import io.lumeer.core.model.types.file_attachment.FileAttachmentType;
import io.lumeer.core.model.types.link.LinkExample;
import io.lumeer.core.model.types.link.LinkType;
import io.lumeer.core.model.types.number.NumberExample;
import io.lumeer.core.model.types.number.NumberType;
import io.lumeer.core.model.types.number.NumberTypeDetail;
import io.lumeer.core.model.types.percentage.PercentageExample;
import io.lumeer.core.model.types.percentage.PercentageType;
import io.lumeer.core.model.types.percentage.PercentageTypeDetail;
import io.lumeer.core.model.types.selection.SelectionExample;
import io.lumeer.core.model.types.selection.SelectionType;
import io.lumeer.core.model.types.selection.SelectionTypeDetail;
import io.lumeer.core.model.types.text.TextExample;
import io.lumeer.core.model.types.text.TextType;
import io.lumeer.core.model.types.text.TextTypeDetail;
import io.lumeer.core.model.types.user.UserExample;
import io.lumeer.core.model.types.user.UserType;

public enum EAiType {

    ADDRESS {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new AddressType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), AddressExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), AddressTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Address;
        }
    },
    CHECK_BOX {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new CheckBoxType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), CheckBoxExample.class)
            );
        }
        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Boolean;
        }
    },
    COLOR {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new ColorType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), ColorExample.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Color;
        }
    },
    COORDINATES {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new CoordinatesType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), CoordinatesExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), CoordinatesTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Coordinates;
        }
    },
    DATE {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new DateType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), DateExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), DateTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.DateTime;
        }
    },
    DURATION {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new DurationType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), DurationExample.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Duration;
        }
    },
    FILE_ATTACHMENT {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            return new FileAttachmentType();
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.FileAttachment;
        }
    },
    LINK {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new LinkType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), LinkExample.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Link;
        }
    },
    NUMBER {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new NumberType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), NumberExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), NumberTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Number;
        }
    },
    PERCENTAGE {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new PercentageType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), PercentageExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), PercentageTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Percentage;
        }
    },
    SELECTION {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new SelectionType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), SelectionExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), SelectionTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Select;
        }
    },
    TEXT {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new TextType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), TextExample.class),
                    objectMapper.readValue(aiTypeDto.getTypeDetail().toString(), TextTypeDetail.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Text;
        }
    },
    USER {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException {
            return new UserType(
                    objectMapper.readValue(aiTypeDto.getExampleValue().toString(), UserExample.class)
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.User;
        }
    };


    public abstract AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) throws JsonProcessingException;
    public abstract ConstraintType getLumeerType();
}
