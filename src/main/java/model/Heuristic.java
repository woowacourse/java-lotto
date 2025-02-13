package model;

/*
    총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
 */

public enum Heuristic {
    BENEFIT("이익"),
    LOSS("손해"),
    NONE("본전");

    private final String benefitType;

    Heuristic(String benefitType) {
        this.benefitType = benefitType;
    }

    public static String determine(double roi) {
        if (roi > 1) {
            return BENEFIT.benefitType;
        }

        if (roi == 1) {
            return NONE.benefitType;
        }

        return LOSS.benefitType;
    }
}
