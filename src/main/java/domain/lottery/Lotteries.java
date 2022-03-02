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
		this.lotteries = lotteriesNumber;
	}

	public static Lotteries from(final List<Lottery> lotteriesNumber) {
		return new Lotteries(deepCopyOf(lotteriesNumber));
	}

	private static List<Lottery> deepCopyOf(final List<Lottery> lotteriesNumber) {
		return lotteriesNumber.stream()
			.collect(Collectors.toList());
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

	public Lotteries add(final List<Lottery> lotteryNumbers) {
		final List<Lottery> lotteries = deepCopyOf(this.lotteries);
		lotteryNumbers.forEach((lottery) ->
			lotteries.add(lottery)
		);
		return new Lotteries(lotteries);
	}
}
