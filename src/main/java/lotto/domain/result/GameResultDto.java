package lotto.domain.result;

import java.util.ArrayList;
import java.util.List;

public class GameResultDto {
	private final List<Statistic> statistics;
	private final double profit;

	public GameResultDto(List<Statistic> statistics, double profit) {
		this.statistics = new ArrayList<>(statistics);
		this.profit = profit;
	}

	public List<Statistic> getStatistics() {
		return statistics;
	}

	public double getProfit() {
		return profit;
	}
}
