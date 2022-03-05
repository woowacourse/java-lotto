package domain;

import java.util.Collections;
import java.util.Map;

import domain.lottery.Lotteries;
import domain.lottery.WinningLottery;

public final class Result {

	private final Map<Rank, Integer> rankResult;
	private final double returnRate;

	private Result(final Lotteries lotteries, final WinningLottery winningLottery) {
		this.rankResult = makeWinner(lotteries, winningLottery);
		this.returnRate = makeReturnRate(lotteries.size());
	}

	public static Result makeResult(final Lotteries lotteries, final WinningLottery winningLottery) {
		return new Result(lotteries, winningLottery);
	}

	public Map<Rank, Integer> getRankResult() {
		return Collections.unmodifiableMap(rankResult);
	}

	public double getReturnRate() {
		return returnRate;
	}

	private Map<Rank, Integer> makeWinner(final Lotteries lotteries, final WinningLottery winningLottery) {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	private double makeReturnRate(final int theNumberOfLottery) {
		final int totalReturn = getTotalReturn();
		return calculateReturnRate(totalReturn, theNumberOfLottery);
	}

	private int getTotalReturn() {
		int totalReturn = 0;
		for (Rank rank : rankResult.keySet()) {
			totalReturn += rankResult.get(rank) * rank.getPrize();
		}
		return totalReturn;
	}

	private double calculateReturnRate(final double totalReturn, final int theNumberOfLottery) {
		return totalReturn / (theNumberOfLottery * LotteryGame.LOTTERY_PRICE);
	}
}
