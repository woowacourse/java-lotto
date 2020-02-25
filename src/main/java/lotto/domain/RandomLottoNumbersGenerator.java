package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumbersGenerator implements RandomGenerator {
	private static final int ZERO_INDEX = 0;
	private static final int SIX_INDEX = 6;

	private static final List<LottoNumber> allLottoNumbers
			= new ArrayList<>(LottoNumber.getAll());

	@Override
	public List<LottoNumber> generateSixNumbers() {
		Collections.shuffle(allLottoNumbers);

		return allLottoNumbers.subList(ZERO_INDEX, SIX_INDEX);
	}
}
