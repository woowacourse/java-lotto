package domain.generatestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LotteryNumberGenerator implements LotteryNumberGeneratorStrategy{

	private static final int MIN_LOTTERY_NUMBER = 1;
	private static final int MAX_LOTTERY_NUMBER = 45;
	private static final int LOTTERY_SIZE = 6;

	private final List<Integer> numbers;

	public LotteryNumberGenerator() {
		numbers = new ArrayList<>();
		for (int i = MIN_LOTTERY_NUMBER; i <= MAX_LOTTERY_NUMBER; i++) {
			numbers.add(i);
		}
	}

	public List<Integer> generateNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(LOTTERY_SIZE)
			.sorted()
			.collect(Collectors.toList());

	}
}
