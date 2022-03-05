package domain.generatestrategy;

import static domain.lottery.LotteryNumber.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.lottery.Lottery;

public class LotteryNumberGenerator implements LotteryNumberGeneratorStrategy{

	private final List<Integer> numbers;

	public LotteryNumberGenerator() {
		numbers = new ArrayList<>();
		for (int i = MIN_LOTTERY_NUMBER; i <= MAX_LOTTERY_NUMBER; i++) {
			numbers.add(i);
		}
	}

	@Override
	public List<Integer> generateNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(Lottery.LOTTERY_SIZE)
			.sorted()
			.collect(Collectors.toList());
	}
}
