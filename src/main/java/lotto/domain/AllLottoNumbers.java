package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllLottoNumbers {
	private static final List<LottoNumber> allLottoNumbers;

	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;

	static {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
		allLottoNumbers = Collections.unmodifiableList(lottoNumbers);
	}

	public static List<LottoNumber> getAll() {
		return Collections.unmodifiableList(allLottoNumbers);
	}
}
