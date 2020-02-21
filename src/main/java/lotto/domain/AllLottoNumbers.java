package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllLottoNumbers {
	private static final List<LottoNumber> allLottoNumbers;

	static {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int i = LottoNumber.MIN; i <= LottoNumber.MAX; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
		allLottoNumbers = Collections.unmodifiableList(lottoNumbers);
	}

	public static List<LottoNumber> getAll() {
		return allLottoNumbers;
	}
}
