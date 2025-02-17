package model;

public enum Heuristic {
    BENEFIT("이익"),
    LOSS("손해"),
    NONE("본전");

    private final String benefitType;

    Heuristic(final String benefitType) {
        this.benefitType = benefitType;
    }

    public static String determine(final double roi) {
        if (roi > 1) {
            return BENEFIT.benefitType;
        }

        if (roi == 1) {
            return NONE.benefitType;
        }

        return LOSS.benefitType;
    }
}
