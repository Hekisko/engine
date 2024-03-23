package io.lumeer.core.model.types.coordinates;

import io.lumeer.core.model.enums.ECoordinatesFormat;

import java.util.HashMap;
import java.util.Map;

public class CoordinatesTypeDetail {

    private ECoordinatesFormat format;
    private int precision;

    public CoordinatesTypeDetail() {
    }

    public CoordinatesTypeDetail(ECoordinatesFormat format, int precision) {
        this.format = format;
        this.precision = precision;
    }

    public ECoordinatesFormat getFormat() {
        return format;
    }

    public void setFormat(ECoordinatesFormat format) {
        this.format = format;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("format", format.equals(ECoordinatesFormat.DECIMAL) ? "DD" : "DMS");
        resultMap.put("precision", precision);

        return resultMap;
    }

    @Override
    public String toString() {
        return "CoordinatesTypeDetail{" +
                "format=" + format +
                ", precision=" + precision +
                '}';
    }
}
