package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.Lotto;

import java.util.Collections;

public class AutoLottoFactory extends AbstractLottoFactory {
	@Override
	public Lotto createSerialLottoNumber() {
		Collections.shuffle(super.getAllLottoNumbers());
		return new Lotto(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
