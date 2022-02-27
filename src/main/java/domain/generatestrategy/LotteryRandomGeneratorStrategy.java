package domain.generatestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import domain.lottery.LotteryNumber;

public class LotteryRandomGeneratorStrategy implements LotteryGenerateFamily {

	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;
	private static final int LOTTERY_SIZE = 6;

	private final List<Integer> numbers;

	public LotteryRandomGeneratorStrategy() {
		numbers = new ArrayList<>();
		for (int i = MIN_LOTTERY_NUMBER; i <= MAX_LOTTERY_NUMBER; i++) {
			numbers.add(i);
		}
	}

	public Set<LotteryNumber> getNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(LOTTERY_SIZE)
			.sorted()
			.map(LotteryNumber::new)
			.collect(Collectors.toCollection(TreeSet::new));
	}
}
