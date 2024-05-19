package io.lumeer.core.model.types.number;

import io.lumeer.core.model.enums.ECoordinatesFormat;

import java.util.HashMap;
import java.util.Map;

public class NumberTypeDetail {

    private boolean canBeNegative;
    private boolean compactMode;
    private String currency;
    private boolean forceSign;
    private Double max;
    private Double min;
    private int roundedToNumberOfDigit;
    private boolean thousandSeparated;

    public NumberTypeDetail() {
        this(
                true,
                false,
                null,
                false,
                null,
                null,
                0,
                false
        );
    }

    public NumberTypeDetail(boolean canBeNegative, boolean compactMode, String currency, boolean forceSign, Double max, Double min, int roundedToNumberOfDigit, boolean thousandSeparated) {
        this.canBeNegative = canBeNegative;
        this.compactMode = compactMode;
        this.currency = currency;
        this.forceSign = forceSign;
        this.max = max;
        this.min = min;
        this.roundedToNumberOfDigit = roundedToNumberOfDigit;
        this.thousandSeparated = thousandSeparated;
    }

    public boolean isCanBeNegative() {
        return canBeNegative;
    }

    public void setCanBeNegative(boolean canBeNegative) {
        this.canBeNegative = canBeNegative;
    }

    public boolean isCompactMode() {
        return compactMode;
    }

    public void setCompactMode(boolean compactMode) {
        this.compactMode = compactMode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isForceSign() {
        return forceSign;
    }

    public void setForceSign(boolean forceSign) {
        this.forceSign = forceSign;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public int getRoundedToNumberOfDigit() {
        return roundedToNumberOfDigit;
    }

    public void setRoundedToNumberOfDigit(int roundedToNumberOfDigit) {
        this.roundedToNumberOfDigit = roundedToNumberOfDigit;
    }

    public boolean isThousandSeparated() {
        return thousandSeparated;
    }

    public void setThousandSeparated(boolean thousandSeparated) {
        this.thousandSeparated = thousandSeparated;
    }

    public Object getConstraints() {
        Map<String, Object> resultMap = new HashMap<>();

        if (min != null) {
            resultMap.put("minValue", min);
        }

        if (max != null) {
            resultMap.put("maxValue", max);
        }

        if (compactMode) {
            resultMap.put("compact", true);
        }

        if (thousandSeparated) {
            resultMap.put("separated", true);
        }

        if (forceSign) {
            resultMap.put("forceSign", true);
        }

        if (canBeNegative) {
            resultMap.put("negative", true);
        }

        if (currency != null) {
            resultMap.put("currency", currency);
        }

        resultMap.put("decimals", roundedToNumberOfDigit);

        return resultMap;
    }

    @Override
    public String toString() {
        return "NumberTypeDetail{" +
                "canBeNegative=" + canBeNegative +
                ", compactMode=" + compactMode +
                ", currency='" + currency + '\'' +
                ", forceSign=" + forceSign +
                ", max=" + max +
                ", min=" + min +
                ", roundedToNumberOfDigit=" + roundedToNumberOfDigit +
                ", thousandSeparated=" + thousandSeparated +
                '}';
    }
}
