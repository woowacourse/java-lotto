package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.Lotto;

import java.util.Collections;

public class AutoSerialLottoNumberFactory extends AbstractSerialLottoNumberFactory {
	@Override
	public Lotto createSerialLottoNumber() {
		Collections.shuffle(super.getAllLottoNumbers());
		return new Lotto(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
