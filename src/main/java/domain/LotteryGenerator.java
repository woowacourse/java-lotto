package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LotteryGenerator {

	private static final int MAX_BOUND = 45;
	private final List<Integer> numbers;

	public LotteryGenerator() {
		numbers = new ArrayList<>();
		for (int i = 1; i <= MAX_BOUND; i++) {
			numbers.add(i);
		}
	}

	public List<Integer> getNumbers() {
		Collections.shuffle(numbers);
		return numbers.stream()
			.limit(6)
			.sorted()
			.collect(Collectors.toList());
	}
}
