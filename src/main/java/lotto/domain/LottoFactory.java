package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoFactory {
	public static Lotto create() {
		List<Number> numbers = Number.getNumbers();
		Collections.shuffle(numbers);
		return new Lotto(numbers.subList(0, 6));
	}
}
