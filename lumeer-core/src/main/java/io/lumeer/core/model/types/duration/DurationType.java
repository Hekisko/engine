package io.lumeer.core.model.types.duration;

import io.lumeer.core.model.enums.EAiType;
import io.lumeer.core.model.enums.ECoordinatesFormat;
import io.lumeer.core.model.types.AbstractType;

import java.util.HashMap;
import java.util.Map;

public class DurationType extends AbstractType {

    private DurationExample exampleValue;

    public DurationType() {
    }

    public DurationType(DurationExample exampleValue) {
        super(EAiType.DURATION);
        this.exampleValue = exampleValue;
    }

    public DurationExample getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(DurationExample exampleValue) {
        this.exampleValue = exampleValue;
    }

    @Override
    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> conversions = new HashMap<>();
        conversions.put("w", 1);
        resultMap.put("type", "classic");
        resultMap.put("conversions", conversions); //TODO ako funguju tieti conversions

        return resultMap;
    }
    @Override
    public String toString() {
        return "DurationType{" +
                "exampleValue=" + exampleValue +
                '}';
    }
}
