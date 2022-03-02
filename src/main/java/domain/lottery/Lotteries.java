package domain.lottery;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.Rank;

public final class Lotteries {

	private static final int INITIAL_RANK_COUNT = 0;

	private final List<Lottery> lotteries;

	private Lotteries(final List<Lottery> lotteriesNumber) {
		List<Lottery> tempLotteriesNumber = deepCopyOf(lotteriesNumber);
		this.lotteries = tempLotteriesNumber;
	}

	private List<Lottery> deepCopyOf(final List<Lottery> lotteriesNumber) {
		List<Lottery> tempLotteriesNumber = lotteriesNumber.stream()
			.collect(Collectors.toList());
		return tempLotteriesNumber;
	}

	public static Lotteries from(final List<Lottery> lotteriesNumber) {
		return new Lotteries(lotteriesNumber);
	}

	public List<Lottery> getLotteries() {
		return Collections.unmodifiableList(lotteries);
	}

	public Map<Rank, Integer> getTheNumberOfWinners(final WinningLottery winningLottery) {
		Map<Rank, Integer> winners = initRankResult();
		lotteries.forEach(lottery -> {
			Rank rank = winningLottery.getRank(lottery);
			putCountToWinner(winners, rank);
		});
		return winners;
	}

	private Map<Rank, Integer> initRankResult() {
		return Rank.valuesWithoutNone().stream()
			.collect(Collectors.toMap(Function.identity(), value -> INITIAL_RANK_COUNT));
	}

	private void putCountToWinner(final Map<Rank, Integer> winners, final Rank rank) {
		if (!rank.equals(Rank.NONE)) {
			winners.put(rank, winners.get(rank) + 1);
		}
	}
}
