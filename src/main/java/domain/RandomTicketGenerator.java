package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTicketGenerator implements TicketGenerator {
	private static final List<Integer> numbers = IntStream.range(1, 46)
		.boxed()
		.collect(Collectors.toList());

	@Override
	public List<Integer> generate() {
		Collections.shuffle(numbers);
		List<Integer> lottoNumbers = numbers.subList(0, 6);
		Collections.sort(lottoNumbers);
		return lottoNumbers;
	}
}
