package lotto.domain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LottoNumberGenerator implements NumberGenerator {
	public static final int LOTTO_NUMBER_SIZE = 6;
	public static final int MAX_LOTTO_NUMBER_RANGE = 45;
	public static final int MIN_LOTTO_NUMBER_RANGE = 1;

	@Override
	public List<Integer> generate() {
		return ThreadLocalRandom.current()
			.ints(MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE)
			.distinct()
			.limit(LOTTO_NUMBER_SIZE)
			.boxed()
			.collect(Collectors.toList());
	}
}
