package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.generatestrategy.LotteryNumberGeneratorStrategy;
import domain.lottery.LotteryGenerator;
import domain.lottery.Lotteries;
import domain.lottery.Lottery;
import domain.lottery.LotteryNumber;
import domain.lottery.WinningLottery;

public final class LotteryGame {

	public static final int LOTTERY_PRICE = 1000;

	private final NumOfLottery numOfLottery;
	private final Money money;
	private final LotteryGenerator lotteryGenerator;
	private final LotteryNumberGeneratorStrategy lotteryNumberGenerator;

	private LotteryGame(final Money money, final NumOfLottery numOfLottery, final LotteryGenerator lotteryGenerator,
		final LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		this.money = money;
		this.numOfLottery = numOfLottery;
		this.lotteryGenerator = lotteryGenerator;
		this.lotteryNumberGenerator = lotteryNumberGenerator;
	}

	public static LotteryGame of(final int inputMoney, final LotteryGenerator lotteryGenerator,
		LotteryNumberGeneratorStrategy lotteryNumberGenerator) {
		Money money = new Money(inputMoney);
		return new LotteryGame(money, NumOfLottery.from(money.divideBy(LOTTERY_PRICE)),
			lotteryGenerator, lotteryNumberGenerator);
	}

	public NumOfLottery getTheNumberOfLottery() {
		return this.numOfLottery;
	}

	public LotteryGame putNumOfManualLottery(final int numOfManualLottery) {
		final NumOfLottery tempNumOfLottery = this.numOfLottery.putNumOfManualLottery(numOfManualLottery);
		return new LotteryGame(this.money, tempNumOfLottery, this.lotteryGenerator, this.lotteryNumberGenerator);
	}

	public Lotteries createLottery(final List<List<Integer>> manualLotteryNumber) {
		final List<Lottery> manualLotteries = manualLotteryNumber.stream()
			.map((numbers) -> lotteryGenerator.generateLottery(numbers))
			.collect(Collectors.toList());
		Lotteries lotteries = Lotteries.from(manualLotteries);
		final List<Lottery> autoLotteries = createLotteriesNumber();
		return lotteries.add(autoLotteries);
	}

	public Lotteries createAutoLottery() {
		final List<Lottery> lotteriesNumber = createLotteriesNumber();
		return Lotteries.from(lotteriesNumber);
	}

	private List<Lottery> createLotteriesNumber() {
		final List<Lottery> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < numOfLottery.getNumOfAutoLottery(); i++) {
			lotteriesNumber.add(lotteryGenerator.generateLottery(lotteryNumberGenerator.generateNumbers()));
		}
		return Collections.unmodifiableList(lotteriesNumber);
	}

	public WinningLottery createWinningLottery(final List<Integer> winningNumbers, final int bonusBall) {
		final Lottery lotteryNumbers = lotteryGenerator.generateLottery(winningNumbers);
		final LotteryNumber bonusLotteryBall = new LotteryNumber(bonusBall);
		return WinningLottery.of(lotteryNumbers, bonusLotteryBall);
	}
}
