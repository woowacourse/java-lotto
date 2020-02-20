package lotto.domain;

public class ProfitCalculator {
	public static double calculate(Money inputMoney, Ranks results) {
		return (double)results.stream()
			.mapToLong(Rank::getAmount)
			.sum() / inputMoney.getLottoMoneyValue();
	}
}
