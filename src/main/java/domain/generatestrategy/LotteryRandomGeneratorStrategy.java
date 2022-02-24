package domain.generatestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.LotteryNumber;

public class LotteryRandomGeneratorStrategy implements LotteryGenerateFamily{

	private static final int MAX_BOUND = 45;
	private final List<Integer> numbers;

	public LotteryRandomGeneratorStrategy() {
		numbers = new ArrayList<>();
		for (int i = 1; i <= MAX_BOUND; i++) {
			numbers.add(i);
		}
	}

	public List<LotteryNumber> getNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(6)
			.sorted()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
	}
}
