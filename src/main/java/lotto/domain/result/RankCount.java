package lotto.domain.result;

public class RankCount {
	private Statistic statistic;
	private int count = 0;

	public RankCount(Statistic statistic) {
		this.statistic = statistic;
	}

	public void plusCount() {
		this.count++;
	}

	public double getProfit() {
		return this.statistic.getPrize() * this.count;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public int getCount() {
		return count;
	}
}
