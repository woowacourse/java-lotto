package lotto.domain.lotto;

import static lotto.domain.lotto.LottoNumber.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoNumberFactory {
	private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

	static {
		for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
			CACHE.put(i, new LottoNumber(i));
		}
	}

	private LottoNumberFactory() {
	}

	static LottoNumber of(int number) {
		if (number >= MIN_BOUND && number <= MAX_BOUND) {
			return CACHE.get(number);
		}
		return new LottoNumber(number);
	}

	public static List<LottoNumber> values() {
		return Collections.unmodifiableList(new ArrayList<>(CACHE.values()));
	}
}
