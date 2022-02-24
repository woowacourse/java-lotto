package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.generatestrategy.LotteryGenerateFamily;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.WinningLottery;

public class LotteryGame {

	private static final int LOTTERY_PRICE = 1000;

	private final int theNumberOfLottery;
	private Lotteries lotteries;
	private LotteryGenerateFamily lotteryGenerator;
	private WinningLottery winningLottery;

	private LotteryGame(final int inputMoney, LotteryGenerateFamily lotteryGenerator) {
		this.theNumberOfLottery = inputMoney / LOTTERY_PRICE;
		this.lotteryGenerator = lotteryGenerator;
		createAutoLottery();
	}

	public static LotteryGame of(final int inputMoney, LotteryGenerateFamily lotteryGenerator) {
		return new LotteryGame(inputMoney, lotteryGenerator);
	}

	private void createAutoLottery() {
		final List<List<Integer>> lotteriesNumber = createLotteriesNumber();
		lotteries = Lotteries.from(lotteriesNumber);
	}

	private List<List<Integer>> createLotteriesNumber() {
		final List<List<Integer>> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		return lotteriesNumber;
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		winningLottery = WinningLottery.of(winningNumbers, bonusBall);
	}

	public Map<Rank, Integer> makeWinner() {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	public double makeReturnRate(final Map<Rank, Integer> rankResult) {
		int totalReturn = getTotalReturn(rankResult);
		return calculateReturnRate(totalReturn);
	}

	private int getTotalReturn(Map<Rank, Integer> rankResult) {
		int totalReturn = 0;
		for (Rank rank : rankResult.keySet()) {
			totalReturn += rankResult.get(rank) * rank.getPrize();
		}
		return totalReturn;
	}

	private double calculateReturnRate(final double totalReturn) {
		return totalReturn / (theNumberOfLottery * LOTTERY_PRICE);
	}

	public List<Lottery> getLotteries() {
		return lotteries.getLotteries();
	}
}
