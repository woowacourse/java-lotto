package lotto.domain;

import java.util.Random;
import java.util.stream.Collectors;

public class LottoGenerator {
	public static Lotto generate() {
		return new Lotto(new Random().ints(Number.MIN_BOUND, Number.MAX_BOUND + 1)
			.distinct()
			.limit(Lotto.CORRECT_SIZE)
			.sorted()
			.mapToObj(Number::valueOf)
			.collect(Collectors.toList()));
	}
}
