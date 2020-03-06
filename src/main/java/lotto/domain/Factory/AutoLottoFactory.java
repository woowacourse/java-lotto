package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoFactory {
	protected static final int ZERO_INDEX = 0;
	protected static final int SIX_INDEX = 6;

	private static final List<LottoNumber> allLottoNumbers;

	static  {
		allLottoNumbers = new ArrayList<>();
		for (int i = LottoNumber.MIN; i <= LottoNumber.MAX; i++) {
			allLottoNumbers.add(LottoNumber.of(i));
		}
	}

	public static Lotto create() {
		Collections.shuffle(allLottoNumbers);
		return Lotto.of(allLottoNumbers.subList(ZERO_INDEX, SIX_INDEX));
	}
}
