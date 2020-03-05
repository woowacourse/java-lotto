package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.Lotto;

public class TestSerialLottoNumberFactory extends AbstractSerialLottoNumberFactory {

	@Override
	public Lotto createSerialLottoNumber() {
		return new Lotto(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
