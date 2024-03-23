package io.lumeer.core.model.types.user;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.enums.ECoordinatesFormat;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;
import java.util.Map;

public class UserType extends AbstractType {

    private UserExample exampleValue;

    public UserType() {
    }

    public UserType(UserExample exampleValue) {
        super(EAiType.USER);
        this.exampleValue = exampleValue;
    }

    public UserExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(UserExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    @Override
    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("externalUsers", true);

        return resultMap;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "exampleValue=" + exampleValue +
                '}';
    }
}
