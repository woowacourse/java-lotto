package lotto.domain.random;

import lotto.domain.number.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Test 를 위해 만든 1 ~ 6의 로또 번호 Set를 항상 반환하는 generator
public class MockLottoNumberGenerator implements RandomGenerator {
	private final List<LottoNumber> allLottoNumbers;

	public MockLottoNumberGenerator() {
		this.allLottoNumbers = new ArrayList<>(LottoNumber.allLottoNumbers());
	}

	@Override
	public Set<LottoNumber> ofSixLottoNumber() {
		return allLottoNumbers.subList(0, 6)
				.stream()
				.collect(Collectors.toUnmodifiableSet());
	}
}
