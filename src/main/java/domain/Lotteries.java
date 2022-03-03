package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotteries {

	private static final int INITIAL_RANKS = 0;

	private final List<Lottery> lotteries;

	public Lotteries(final List<Lottery> lotteries) {
		this.lotteries = lotteries;
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}

	public void addLotteries(final List<Lottery> newLotteries) {
		lotteries.addAll(newLotteries);
	}

	public Map<Rank, Integer> getTheNumberOfWinners(final WinningLottery winningLottery) {
		Map<Rank,Integer> winners = initRankResult();
		lotteries.forEach(lottery -> {
			Rank rank = winningLottery.getRank(lottery);
			putCountToWinner(winners, rank);
		});
		return winners;
	}

	private Map<Rank, Integer> initRankResult() {
		final Map<Rank, Integer> rankResult = new HashMap<>();
		Rank.getValuesExceptNoneRank()
			.forEach((rank) -> rankResult.put(rank, INITIAL_RANKS));
		return rankResult;
	}

	private void putCountToWinner(final Map<Rank, Integer> winners, final Rank rank) {
		if (!rank.equals(Rank.NONE)) {
			winners.merge(rank, 1, Integer::sum);
		}
	}

}
