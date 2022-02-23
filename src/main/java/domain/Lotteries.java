package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotteries {

	private final List<Lottery> lotteries;

	public Lotteries(final List<List<Integer>> lotteriesNumber) {
		lotteries = new ArrayList<>();
		lotteriesNumber.forEach(lotteryNumber ->
			lotteries.add(new Lottery(lotteryNumber)));
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}

	public Map<Rank, Integer> getTheNumberOfWinners(final WinningLottery winningLottery) {
		Map<Rank,Integer> winners = initRankResult();
		lotteries.forEach(lottery -> {
			Rank rank = winningLottery.getRank(lottery);
			putCountToWinner(winners, rank);
		});
		// List<Rank> ranks = new ArrayList<>();
		// lotteries.forEach(lottery -> {
		// 	ranks.add(winningLottery.getRank(lottery));
		// });
		// ranks.stream().filter(rank -> rank != Rank.NONE)
		// 	.forEach(rank -> winners.get(rank) + 1);
		return winners;
	}

	private void putCountToWinner(Map<Rank, Integer> winners, Rank rank) {
		if (!rank.equals(Rank.NONE)) {
			winners.put(rank, winners.get(rank) + 1);
		}
	}

	private Map<Rank, Integer> initRankResult() {
		final Map<Rank, Integer> rankResult = new HashMap<>();
		Rank.getValues()
			.forEach((rank) -> rankResult.put(rank, 0));
		return rankResult;
	}
}
