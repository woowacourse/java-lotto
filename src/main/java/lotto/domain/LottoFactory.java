package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static List<Number> numbers;

	static {
		numbers = new ArrayList<>();
		for (int i = 1; i < 46; i++) {
			numbers.add(new Number(String.valueOf(i)));
		}
	}

	public static Lotto create() {
		Collections.shuffle(numbers);
		return new Lotto(numbers.subList(0, 6));
	}


}
