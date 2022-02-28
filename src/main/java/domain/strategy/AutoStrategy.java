package domain.strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Ball;

public class AutoStrategy implements TicketGenerateStrategy {
	private static final int LOTTO_PARSE_START_RANGE = 0;
	private static final int LOTTO_PARSE_END_RANGE = 6;

	private static final List<Integer> numbers = IntStream.range(Ball.MIN_NUMBER, Ball.MAX_NUMBER + 1)
		.boxed()
		.collect(Collectors.toList());

	@Override
	public List<Integer> generate() {
		Collections.shuffle(numbers);
		List<Integer> lottoNumbers = numbers.subList(LOTTO_PARSE_START_RANGE, LOTTO_PARSE_END_RANGE);
		Collections.sort(lottoNumbers);

		return lottoNumbers;
	}
}
