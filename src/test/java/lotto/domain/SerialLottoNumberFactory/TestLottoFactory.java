package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.Lotto;

public class TestLottoFactory extends AbstractLottoFactory {

	@Override
	public Lotto createLotto() {
		return Lotto.of(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
