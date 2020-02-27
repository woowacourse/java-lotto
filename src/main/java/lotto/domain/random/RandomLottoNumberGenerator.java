package lotto.domain.random;

import lotto.domain.number.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomLottoNumberGenerator implements RandomGenerator {
	private static final int ZERO = 0;
	private static final int SIX = 6;

	private final List<LottoNumber> allLottoNumbers;

	public RandomLottoNumberGenerator() {
		this.allLottoNumbers = new ArrayList<>(LottoNumber.allLottoNumbers());
	}

	@Override
	public Set<LottoNumber> ofSixLottoNumber() {
		Collections.shuffle(allLottoNumbers);

		return allLottoNumbers.subList(ZERO, SIX)
				.stream()
				.collect(Collectors.toUnmodifiableSet());
	}
}
