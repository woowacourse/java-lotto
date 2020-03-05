package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.Lotto;

import java.util.Collections;

public class AutoLottoFactory extends AbstractLottoFactory {
	@Override
	public Lotto createSerialLottoNumber() {
		Collections.shuffle(super.getAllLottoNumbers());
		return Lotto.of(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
