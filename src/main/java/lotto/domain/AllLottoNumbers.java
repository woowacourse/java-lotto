package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllLottoNumbers {
	private static final int MIN = 1;
	private static final int MAX = 45;

	private static final Map<Integer, LottoNumber> allLottoNumbers;

	static {
		Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
		for (int i = MIN; i <= MAX; i++) {
			lottoNumbers.put(i, new LottoNumber(i));
		}
		allLottoNumbers = Collections.unmodifiableMap(lottoNumbers);
	}

	public static List<LottoNumber> getAll() {
		return allLottoNumbers.values()
				.stream()
				.collect(Collectors.toUnmodifiableList());
	}

	public static LottoNumber getLottoNumber(int number) {
		checkIsWithinRange(number);
		return allLottoNumbers.get(number);
	}

	private static void checkIsWithinRange(int number) {
		if (number < MIN || number > MAX) {
			throw new LottoNumberIllegalArgumentException(number);
		}
	}
}
