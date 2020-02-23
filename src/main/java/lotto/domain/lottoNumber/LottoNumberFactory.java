package lotto.domain.lottoNumber;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoNumberFactory {
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;
	private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();

	static {
		for (int lottoNumber = MIN_LOTTO_NUMBER; lottoNumber <= MAX_LOTTO_NUMBER; lottoNumber++) {
			CACHE.put(lottoNumber, new LottoNumber(lottoNumber));
		}
	}

	public static LottoNumber generate(int number) {
		if (number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER) {
			return CACHE.get(number);
		}
		return new LottoNumber(number);
	}

	public static List<LottoNumber> generate(int... numbers) {
		return Arrays.stream(numbers)
			.mapToObj(LottoNumberFactory::generate)
			.collect(Collectors.toList());
	}

	public static Collection<LottoNumber> values() {
		return Collections.unmodifiableCollection(CACHE.values());
	}
}
