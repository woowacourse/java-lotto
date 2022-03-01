package domain;

import java.util.Map;

import domain.lottery.Lotteries;
import domain.lottery.WinningLottery;

public class Result {

	private Map<Rank, Integer> rankResult;
	private double returnRate;

	public Map<Rank, Integer> getRankResult() {
		return rankResult;
	}

	public double getReturnRate() {
		return returnRate;
	}

	public void makeWinner(Lotteries lotteries, WinningLottery winningLottery) {
		this.rankResult = lotteries.getTheNumberOfWinners(winningLottery);
	}

	public void makeReturnRate(LotteryGame lotteryGame) {
		final int totalReturn = getTotalReturn();
		this.returnRate = calculateReturnRate(totalReturn, lotteryGame.getTheNumberOfLottery());
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
