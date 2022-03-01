package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import domain.generatestrategy.LotteryNumberGeneratorStrategy;
import domain.lottery.LotteryGenerator;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.LotteryNumber;
import domain.lottery.WinningLottery;

public class LotteryGame {

	private static final int LOTTERY_PRICE = 1000;

	private final int theNumberOfLottery;
	private final Money money;
	private final LotteryGenerator lotteryGenerator;
	private final LotteryNumberGeneratorStrategy lotteryNumberGenerator;
	private Lotteries lotteries;
	private WinningLottery winningLottery;

	private LotteryGame(final int inputMoney, final LotteryGenerator lotteryGenerator,
		final LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		this.money = new Money(inputMoney);
		this.theNumberOfLottery = money.divideBy(LOTTERY_PRICE);
		this.lotteryGenerator = lotteryGenerator;
		this.lotteryNumberGenerator = lotteryNumberGenerator;
		createAutoLottery();
	}

	public static LotteryGame of(final int inputMoney, final LotteryGenerator lotteryGenerator,
		LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		return new LotteryGame(inputMoney, lotteryGenerator, lotteryNumberGenerator);
	}

	private void createAutoLottery() {
		final List<Lottery> lotteriesNumber = createLotteriesNumber();
		lotteries = Lotteries.from(lotteriesNumber);
	}

	private List<Lottery> createLotteriesNumber() {
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.generateLottery(lotteryNumberGenerator.generateNumbers()));
		}
		return lotteriesNumber;
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		final Lottery lotteryNumbers = lotteryGenerator.generateLottery(winningNumbers);
		final LotteryNumber bonusLotteryBall = new LotteryNumber(bonusBall);
		winningLottery = WinningLottery.of(lotteryNumbers, bonusLotteryBall);
	}

	public Map<Rank, Integer> makeWinner() {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	public double makeReturnRate(final Map<Rank, Integer> rankResult) {
		final int totalReturn = getTotalReturn(rankResult);
		return calculateReturnRate(totalReturn);
	}

	private int getTotalReturn(final Map<Rank, Integer> rankResult) {
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
