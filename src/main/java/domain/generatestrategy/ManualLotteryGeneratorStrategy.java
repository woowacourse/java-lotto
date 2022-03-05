package domain.generatestrategy;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lottery;
import domain.LotteryNumber;
import domain.factory.LotteryNumberFactory;

public class ManualLotteryGeneratorStrategy implements LotteryGenerateStrategy {

	private final LotteryNumberFactory lotteryNumberFactory;
	private final List<Integer> rawManualLotteries;

	public ManualLotteryGeneratorStrategy(final List<Integer> rawManualLotteries, final LotteryNumberFactory lotteryNumberFactory) {
		this.lotteryNumberFactory = lotteryNumberFactory;
		this.rawManualLotteries = rawManualLotteries;
	}

	@Override
	public Lottery getLottery() {
		List<LotteryNumber> manualLotteries = rawManualLotteries.stream()
			.map(lotteryNumberFactory::of)
			.collect(Collectors.toList());
		return new Lottery(manualLotteries);
	}

}
