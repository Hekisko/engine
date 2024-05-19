package io.lumeer.core.model.types.date;

import io.lumeer.core.model.enums.ECoordinatesFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTypeDetail {

    private String format;
    private boolean isUTC;
    private Long maximum;
    private Long minimum;

    public DateTypeDetail() {
        this("DD.MM.YYYY", false, null, null);
    }

    public DateTypeDetail(String format, boolean isUTC, Long maximum, Long minimum) {
        this.format = format;
        this.isUTC = isUTC;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isUTC() {
        return isUTC;
    }

    public void setUTC(boolean UTC) {
        isUTC = UTC;
    }

    public long getMaximum() {
        return maximum;
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public long getMinimum() {
        return minimum;
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("format", format);
        resultMap.put("asUtc", isUTC);
        if (minimum != null) {
            resultMap.put("minValue", new Date(minimum));
        }
        if (maximum != null) {
            resultMap.put("maxValue", new Date(maximum));
        }

        return resultMap;
    }

    @Override
    public String toString() {
        return "DateTypeDetail{" +
                "format='" + format + '\'' +
                ", isUTC=" + isUTC +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}
