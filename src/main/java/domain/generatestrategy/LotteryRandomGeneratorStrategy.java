package domain.generatestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Lottery;
import domain.LotteryNumber;

public class LotteryRandomGeneratorStrategy implements LotteryGenerateStrategy {

	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;
	private static final int PROPER_LOTTERY_NUMBERS = 6;
	private final List<LotteryNumber> numbers;

	public LotteryRandomGeneratorStrategy() {
		numbers = IntStream.rangeClosed(MIN_BOUND, MAX_BOUND)
			.mapToObj(LotteryNumber::new)
			.collect(Collectors.toList());
	}

	public Lottery getNumbers() {
		Collections.shuffle(numbers);
		List<LotteryNumber> lotteryNumbers =  numbers.stream()
			.limit(PROPER_LOTTERY_NUMBERS)
			.sorted()
			.collect(Collectors.toList());
		return new Lottery(lotteryNumbers);
	}
}
