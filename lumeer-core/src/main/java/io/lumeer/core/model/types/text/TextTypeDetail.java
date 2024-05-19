package io.lumeer.core.model.types.text;


import java.util.HashMap;
import java.util.Map;

public class TextTypeDetail {

    private int maximumLength;
    private int minimumLength;
    public TextTypeDetail() {
        this(300, 0);
    }

    public TextTypeDetail(int maximumLength, int minimumLength) {
        this.maximumLength = maximumLength;
        this.minimumLength = minimumLength;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        if (minimumLength > 0) {
            resultMap.put("minLength", minimumLength);
        }

        if (maximumLength > 0) {
            resultMap.put("maxLength", maximumLength);
        }

        return resultMap;
    }

    @Override
    public String toString() {
        return "TextTypeDetail{" +
                "maximumLength=" + maximumLength +
                ", minimumLength=" + minimumLength +
                '}';
    }
}
