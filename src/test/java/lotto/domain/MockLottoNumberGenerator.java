package lotto.domain;

import java.util.List;

public class MockLottoNumberGenerator implements RandomGenerator {
	@Override
	public List<LottoNumber> generateSixNumbers() {
		return LottoNumber.getAll().subList(0, 6);
	}
}
