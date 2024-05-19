package io.lumeer.core.model.types.percentage;

import io.lumeer.core.model.enums.ECoordinatesFormat;
import io.lumeer.core.model.enums.EPercentageFormat;

import java.util.HashMap;
import java.util.Map;


public class PercentageTypeDetail {

    private String color;
    private EPercentageFormat display;
    private Double maximum;
    private Double minimum;
    private int roundedToNumberOfDigit;
    public PercentageTypeDetail() {
        this(
                "#ff0000",
                EPercentageFormat.PROGRESS_BAR,
                100.,
                0.,
                0
        );
    }

    public PercentageTypeDetail(String color, EPercentageFormat display, Double maximum, Double minimum, int roundedToNumberOfDigit) {
        this.color = color;
        this.display = display;
        this.maximum = maximum;
        this.minimum = minimum;
        this.roundedToNumberOfDigit = roundedToNumberOfDigit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public EPercentageFormat getDisplay() {
        return display;
    }

    public void setDisplay(EPercentageFormat display) {
        this.display = display;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public int getRoundedToNumberOfDigit() {
        return roundedToNumberOfDigit;
    }

    public void setRoundedToNumberOfDigit(int roundedToNumberOfDigit) {
        this.roundedToNumberOfDigit = roundedToNumberOfDigit;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("decimals", roundedToNumberOfDigit);

        if (minimum != null) {
            resultMap.put("minValue", minimum);
        }

        if (maximum != null) {
            resultMap.put("maxValue", maximum);
        }

        resultMap.put("style", display.equals(EPercentageFormat.PROGRESS_BAR) ? "ProgressBar" : "Text");

        if (color != null) {
            resultMap.put("color", color);
        }

        return resultMap;
    }

    @Override
    public String toString() {
        return "PercentageTypeDetail{" +
                "color='" + color + '\'' +
                ", display=" + display +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                ", roundedToNumberOfDigit=" + roundedToNumberOfDigit +
                '}';
    }
}
