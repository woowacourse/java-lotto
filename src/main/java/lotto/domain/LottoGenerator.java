package lotto.domain;

import java.util.Random;
import java.util.stream.Collectors;

public class LottoGenerator {
	public static Lotto generate() {
		return new Lotto(new Random().ints(LottoNumber.MIN_BOUND, LottoNumber.MAX_BOUND + 1)
			.distinct()
			.limit(Lotto.CORRECT_SIZE)
			.sorted()
			.mapToObj(LottoNumber::valueOf)
			.collect(Collectors.toList()));
	}
}
