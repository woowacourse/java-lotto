package lotto.domain;

public class Profit {
	public static final int PERCENTAGE = 100;

	private double profit = 0;

	public void add(double input) {
		profit += input;
	}

	public double getProfit(PurchaseMoney money) {
		return (profit / money.getPurchaseMoney()) * PERCENTAGE;
	}
}
