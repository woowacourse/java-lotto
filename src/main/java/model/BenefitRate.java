package model;

public class BenefitRate {

    private final double number;

    public BenefitRate(final LottoPurchase lottoPurchase, final int benefit) {
        this.number = (double) benefit / lottoPurchase.amount();
    }

    public double getNumber() {
        return number;
    }
}
