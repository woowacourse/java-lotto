package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.generatestrategy.LotteryGenerateFamily;

public class LotteryGame {

	private final int theNumberOfLottery;
	private Lotteries lotteries;
	private LotteryGenerateFamily lotteryGenerator;
	private WinningLottery winningLottery;

	public LotteryGame(int theNumberOfLottery, LotteryGenerateFamily lotteryGenerator) {
		this.theNumberOfLottery = theNumberOfLottery;
		this.lotteryGenerator = lotteryGenerator;
		createAutoLottery();
	}

	private void createAutoLottery() {
		final List<List<Integer>> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		lotteries = new Lotteries(lotteriesNumber);
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		winningLottery = new WinningLottery(winningNumbers, bonusBall);
	}

	public Map<Rank, Integer> makeWinner() {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	public double makeRankingPercent(final Map<Rank, Integer> rankResult) {
		int incomeRate = 0;
		for (Rank rank : rankResult.keySet()) {
			incomeRate += rankResult.get(rank) * rank.getPrize();
		}
		return (double)incomeRate / (theNumberOfLottery * 1000);
	}

	public List<Lottery> getLotteries() {
		return lotteries.getLotteries();
	}
}
