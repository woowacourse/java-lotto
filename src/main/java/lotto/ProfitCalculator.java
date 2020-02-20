package lotto;

import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Ranks;

public class ProfitCalculator {
	public static double calculate(Money inputMoney, Ranks results) {
		return (double)results.stream()
			.mapToInt(Rank::getAmount).sum() /
			inputMoney.getLottoMoneyValue();
	}
}
