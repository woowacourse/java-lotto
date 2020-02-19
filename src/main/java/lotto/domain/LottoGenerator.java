package lotto.domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LottoGenerator {
	public static Lotto generate() {
		List<Number> numbers = new Random().ints(1, 45)
			.distinct()
			.limit(6)
			.mapToObj(Number::valueOf)
			.collect(Collectors.toList());
		return new Lotto(numbers);
	}
}
