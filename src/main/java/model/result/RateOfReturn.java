package model.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.lotto.LottoCount;

public class RateOfReturn {
	private static final int UNIT = 1000;
	private final int money;
	private int sumMoneyOfReturns;
	private Map<Statistics, Integer> countOfResult;

	public RateOfReturn(LottoCount lottoCount) {
		this.money = lottoCount.getCount() * UNIT;
		this.sumMoneyOfReturns = 0;
		countOfResult = new HashMap<>();
	}

	public void saveResult(Statistics statistics) {
		countOfResult.put(statistics, countOfResult.getOrDefault(statistics, 0) + 1);
	}

	public int countStatistics(Statistics statistics) {
		return countOfResult.getOrDefault(statistics, 0);
	}

	public double getRateOfReturn() {
		Arrays.stream(Statistics.values())
			.forEach(
				statistics -> sumMoneyOfReturns += (countOfResult.getOrDefault(statistics, 0) * statistics.getValue()));

		return (sumMoneyOfReturns / (double)money);
	}
}
