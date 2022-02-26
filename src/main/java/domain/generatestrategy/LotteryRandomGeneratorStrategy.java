package domain.generatestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.LotteryNumber;

public class LotteryRandomGeneratorStrategy implements LotteryGenerateFamily{

	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;
	private static final int PROPER_LOTTERY_NUMBERS = 6;
	private final List<Integer> numbers;

	public LotteryRandomGeneratorStrategy() {
		numbers = new ArrayList<>();
		for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
			numbers.add(i);
		}
	}

	public List<LotteryNumber> getNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(PROPER_LOTTERY_NUMBERS)
			.sorted()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
	}
}
