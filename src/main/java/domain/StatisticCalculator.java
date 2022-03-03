package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatisticCalculator {
	private static final int UNIT_PRICE = 1000;
	private static final double INT_TO_DOUBLE = 1.0;
	private final Map<WinningStatus, Integer> resultStatistics = new LinkedHashMap<>();

	public StatisticCalculator() {
		for (WinningStatus winningStatus : WinningStatus.getValues()) {
			this.resultStatistics.put(winningStatus, 0);
		}
	}

	public void updateResult(LottoTickets lottoTickets, AnswerLotto answerLotto) {
		for (WinningStatus winningStatus : lottoTickets.calculateAllLottoResult(answerLotto)) {
			int count = this.resultStatistics.get(winningStatus);
			this.resultStatistics.put(winningStatus, count + 1);
		}
	}

	public double calculateProfitRatio() {
		long totalPrice =
			this.resultStatistics
				.keySet()
				.stream()
				.filter(num -> this.resultStatistics.get(num) > 0)
				.mapToLong(this.resultStatistics::get)
				.sum() * UNIT_PRICE;
		int totalPrize = this.resultStatistics.keySet().stream().filter(num -> this.resultStatistics.get(num) > 0)
			.mapToInt(num -> num.getProfit() * this.resultStatistics.get(num)).sum();
		return INT_TO_DOUBLE * totalPrize / totalPrice;
	}

	public List<Integer> getCount() {
		List<Integer> count = new ArrayList<>();
		for (WinningStatus winningStatus : this.resultStatistics.keySet()) {
			count.add(this.resultStatistics.get(winningStatus));
		}
		return count;
	}
}
