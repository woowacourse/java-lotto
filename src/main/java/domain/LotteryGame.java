package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import domain.generatestrategy.LotteryGenerateFamily;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.LotteryNumber;
import domain.lottery.WinningLottery;

public class LotteryGame {

	private static final int LOTTERY_PRICE = 1000;

	private final int theNumberOfLottery;
	private final LotteryGenerateFamily lotteryGenerator;
	private Lotteries lotteries;
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
		final List<Set<LotteryNumber>> lotteriesNumber = createLotteriesNumber();
		lotteries = Lotteries.from(lotteriesNumber);
	}

	private List<Set<LotteryNumber>> createLotteriesNumber() {
		final List<Set<LotteryNumber>> lotteriesNumber = new ArrayList<>(new TreeSet<>());
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		return lotteriesNumber;
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		final Set<LotteryNumber> lotteryNumbers = generateLotteryNumbers(winningNumbers);
		final LotteryNumber bonusLotteryBall = new LotteryNumber(bonusBall);
		winningLottery = WinningLottery.of(lotteryNumbers, bonusLotteryBall);
	}

	private Set<LotteryNumber> generateLotteryNumbers(final List<Integer> winningNumbers) {
		return winningNumbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toCollection(TreeSet::new));
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
