package domain;

public enum InvestmentOutcome {
    BENEFIT("이익"),
    LOSS("손해"),
    NONE("본전");

    private final String benefitType;

    InvestmentOutcome(String benefitType) {
        this.benefitType = benefitType;
    }

    public static String determine(final double returnOfInvestment) {
        if (returnOfInvestment > 1) {
            return BENEFIT.benefitType;
        }

        if (returnOfInvestment == 1) {
            return NONE.benefitType;
        }

        return LOSS.benefitType;
    }
}
