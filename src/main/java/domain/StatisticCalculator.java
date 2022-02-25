package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatisticCalculator {
	private static final int UNIT_PRICE = 1000;
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
		int totalPrice =
			this.resultStatistics
				.keySet()
				.stream()
				.filter(num -> this.resultStatistics.get(num) > 0)
				.mapToInt(this.resultStatistics::get)
				.sum() * UNIT_PRICE;
		int totalPrize = this.resultStatistics.keySet().stream().filter(num -> this.resultStatistics.get(num) > 0)
			.mapToInt(WinningStatus::getProfit).sum();
		return totalPrize / totalPrice;
	}

	public List<Integer> getCount() {
		List<Integer> count = new ArrayList<>();
		for (WinningStatus winningStatus : this.resultStatistics.keySet()) {
			count.add(this.resultStatistics.get(winningStatus));
		}
		return count;
	}
}
