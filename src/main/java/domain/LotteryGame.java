package domain;

import java.util.ArrayList;
import java.util.List;

import domain.generatestrategy.LotteryNumberGeneratorStrategy;
import domain.lottery.LotteryGenerator;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.LotteryNumber;
import domain.lottery.WinningLottery;

public class LotteryGame {

	public static final int LOTTERY_PRICE = 1000;

	private final int theNumberOfLottery;
	private final Money money;
	private final LotteryGenerator lotteryGenerator;
	private final LotteryNumberGeneratorStrategy lotteryNumberGenerator;

	private LotteryGame(final int inputMoney, final LotteryGenerator lotteryGenerator,
		final LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		this.money = new Money(inputMoney);
		this.theNumberOfLottery = money.divideBy(LOTTERY_PRICE);
		this.lotteryGenerator = lotteryGenerator;
		this.lotteryNumberGenerator = lotteryNumberGenerator;
	}

	public static LotteryGame of(final int inputMoney, final LotteryGenerator lotteryGenerator,
		LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		return new LotteryGame(inputMoney, lotteryGenerator, lotteryNumberGenerator);
	}

	public int getTheNumberOfLottery() {
		return theNumberOfLottery;
	}

	public Lotteries createAutoLottery() {
		final List<Lottery> lotteriesNumber = createLotteriesNumber();
		return Lotteries.from(lotteriesNumber);
	}

	private List<Lottery> createLotteriesNumber() {
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.generateLottery(lotteryNumberGenerator.generateNumbers()));
		}
		return lotteriesNumber;
	}

	public WinningLottery createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		final Lottery lotteryNumbers = lotteryGenerator.generateLottery(winningNumbers);
		final LotteryNumber bonusLotteryBall = new LotteryNumber(bonusBall);
		return WinningLottery.of(lotteryNumbers, bonusLotteryBall);
	}
}
