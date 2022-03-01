package domain.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Rank;

public class Lotteries {

	private final List<Lottery> lotteries;

	private Lotteries(final List<Lottery> lotteriesNumber) {
		this.lotteries = lotteriesNumber;
	}

	public static Lotteries from(final List<Lottery> lotteriesNumber) {
		return new Lotteries(lotteriesNumber);
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}

	public Map<Rank, Integer> getTheNumberOfWinners(final WinningLottery winningLottery) {
		Map<Rank, Integer> winners = initRankResult();
		lotteries.forEach(lottery -> {
			Rank rank = winningLottery.getRank(lottery);
			putCountToWinner(winners, rank);
		});
		return winners;
	}

	private void putCountToWinner(final Map<Rank, Integer> winners, final Rank rank) {
		if (!rank.equals(Rank.NONE)) {
			winners.put(rank, winners.get(rank) + 1);
		}
	}

	private Map<Rank, Integer> initRankResult() {
		final Map<Rank, Integer> rankResult = new HashMap<>();
		Rank.valuesWithoutNone()
			.forEach((rank) -> rankResult.put(rank, 0));
		return rankResult;
	}
}
