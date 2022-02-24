package domain.strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTicketGenerator implements TicketGenerator {
	private static final int TOTAL_START_RANGE = 1;
	private static final int TOTAL_END_RANGE = 45;
	private static final int LOTTO_PARSE_START_RANGE = 0;
	private static final int LOTTO_PARSE_END_RANGE = 6;

	private static final List<Integer> numbers = IntStream.range(TOTAL_START_RANGE, TOTAL_END_RANGE + 1)
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
