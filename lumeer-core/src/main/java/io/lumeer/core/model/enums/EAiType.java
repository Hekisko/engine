package io.lumeer.core.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            AddressExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    AddressExample.class,
                    new AddressExample()
            );
            AddressTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    AddressTypeDetail.class,
                    new AddressTypeDetail()
            );

            return new AddressType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Address;
        }
    },
    CHECK_BOX {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            CheckBoxExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    CheckBoxExample.class,
                    new CheckBoxExample()
            );

            return new CheckBoxType(example);
        }
        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Boolean;
        }
    },
    COLOR {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            ColorExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    ColorExample.class,
                    new ColorExample()
            );

            return new ColorType(example);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Color;
        }
    },
    COORDINATES {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            CoordinatesExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    CoordinatesExample.class,
                    new CoordinatesExample()
            );
            CoordinatesTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    CoordinatesTypeDetail.class,
                    new CoordinatesTypeDetail()
            );

            return new CoordinatesType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Coordinates;
        }
    },
    DATE {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            DateExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    DateExample.class,
                    new DateExample()
            );
            DateTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    DateTypeDetail.class,
                    new DateTypeDetail()
            );

            return new DateType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.DateTime;
        }
    },
    DURATION {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            DurationExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    DurationExample.class,
                    new DurationExample()
            );

            return new DurationType(example);
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
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            LinkExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    LinkExample.class,
                    new LinkExample()
            );

            return new LinkType(example);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Link;
        }
    },
    NUMBER {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            NumberExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    NumberExample.class,
                    new NumberExample()
            );
            NumberTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    NumberTypeDetail.class,
                    new NumberTypeDetail()
            );

            return new NumberType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Number;
        }
    },
    PERCENTAGE {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            PercentageExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    PercentageExample.class,
                    new PercentageExample()
            );
            PercentageTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    PercentageTypeDetail.class,
                    new PercentageTypeDetail()
            );

            return new PercentageType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Percentage;
        }
    },
    SELECTION {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            SelectionExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    SelectionExample.class,
                    new SelectionExample()
            );
            SelectionTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    SelectionTypeDetail.class,
                    new SelectionTypeDetail()
            );

            return new SelectionType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Select;
        }
    },
    TEXT {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            TextExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    TextExample.class,
                    new TextExample()
            );
            TextTypeDetail typeDetail = parseAndValidate(
                    aiTypeDto.getTypeDetail(),
                    objectMapper,
                    TextTypeDetail.class,
                    new TextTypeDetail()
            );

            return new TextType(example, typeDetail);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Text;
        }
    },
    USER {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            UserExample example = parseAndValidate(
                    aiTypeDto.getExampleValue(),
                    objectMapper,
                    UserExample.class,
                    new UserExample()
            );

            return new UserType(example);
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.User;
        }
    },
    @JsonEnumDefaultValue UNKNOWN {
        @Override
        public AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper) {
            return new TextType(
                    new TextExample(
                            "Unknown type"
                    ),
                    new TextTypeDetail(
                            100, 0
                    )
            );
        }

        @Override
        public ConstraintType getLumeerType() {
            return ConstraintType.Text;
        }
    };

    public static boolean isValid(JsonNode node) {
        return node != null && !"null".equalsIgnoreCase(node.toString());
    }

    public static <T> T parseAndValidate(JsonNode value,
                                         ObjectMapper objectMapper,
                                         Class<T> valueType,
                                         T defaultInstance) {
        if (EAiType.isValid(value)) {
            try {
                return objectMapper.readValue(value.toString(), valueType);
            } catch (JsonProcessingException | NullPointerException ignored) {
                return defaultInstance;
            }
        } else {
            return defaultInstance;
        }
    }

    public abstract AbstractType getType(AiTypeDto aiTypeDto, ObjectMapper objectMapper);
    public abstract ConstraintType getLumeerType();
}
