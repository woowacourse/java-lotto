package lotto.domain;

import java.util.List;

public class ProfitCalculator {
	public static double calculate(Money inputMoney, List<Rank> rankList) {
		return (double)rankList.stream()
			.mapToLong(Rank::getAmount)
			.sum() / inputMoney.getLottoMoneyValue();
	}
}
