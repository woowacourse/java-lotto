package domain.generatestrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Lottery;
import domain.LotteryNumber;
import domain.factory.LotteryNumberFactory;

public class RandomLotteryGeneratorStrategy implements LotteryGenerateStrategy {

	private final List<LotteryNumber> numbers;

	public RandomLotteryGeneratorStrategy() {
		LotteryNumberFactory lotteryNumberFactory = new LotteryNumberFactory();
		numbers = IntStream.rangeClosed(LotteryNumber.NUMBER_MIN_RANGE, LotteryNumber.NUMBER_MAX_RANGE)
			.mapToObj(lotteryNumberFactory::of)
			.collect(Collectors.toList());
	}

	public Lottery getLottery() {
		Collections.shuffle(numbers);
		List<LotteryNumber> lotteryNumbers =  numbers.stream()
			.limit(Lottery.LOTTERY_SIZE)
			.sorted()
			.collect(Collectors.toList());
		return new Lottery(lotteryNumbers);
	}
}
