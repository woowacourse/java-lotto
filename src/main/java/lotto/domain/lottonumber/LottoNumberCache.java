package lotto.domain.lottonumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoNumberCache {
	private static Map<Integer, LottoNumber> CACHE = new HashMap<>();

	static {
		for (int i = LottoNumber.MIN_BOUND; i <= LottoNumber.MAX_BOUND; i++) {
			CACHE.put(i, new LottoNumber(i));
		}
	}

	private LottoNumberCache() {
	}

	public static LottoNumber getNumber(int number) {
		if (LottoNumber.MIN_BOUND < number && LottoNumber.MAX_BOUND > number) {
			return CACHE.get(number);
		}
		return new LottoNumber(number);
	}

	public static List<LottoNumber> values() {
		return Collections.unmodifiableList(new ArrayList<>(CACHE.values()));
	}
}
