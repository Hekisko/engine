package io.lumeer.core.model.types.selection;

import io.lumeer.core.model.enums.ECoordinatesFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectionTypeDetail {

    private boolean allowMultipleValues;
    private String name;
    private List<SelectionValue> values;

    public SelectionTypeDetail() {
    }

    public SelectionTypeDetail(boolean allowMultipleValues, String name, List<SelectionValue> values) {
        this.allowMultipleValues = allowMultipleValues;
        this.name = name;
        this.values = values;
    }

    public boolean isAllowMultipleValues() {
        return allowMultipleValues;
    }

    public void setAllowMultipleValues(boolean allowMultipleValues) {
        this.allowMultipleValues = allowMultipleValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SelectionValue> getValues() {
        return values;
    }

    public void setValues(List<SelectionValue> values) {
        this.values = values;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        if (allowMultipleValues) {
            resultMap.put("multi", true);
        }

        resultMap.put("options", values.stream().map(SelectionValue::getConstraints).toList().toArray());

        return resultMap;
    }

    @Override
    public String toString() {
        return "SelectionTypeDetail{" +
                "allowMultipleValues=" + allowMultipleValues +
                ", name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
