package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.SerialLottoNumber;

import java.util.Collections;

public class AutoSerialLottoNumberFactory extends AbstractSerialLottoNumberFactory {
	@Override
	public SerialLottoNumber createSerialLottoNumber() {
		Collections.shuffle(super.getAllLottoNumbers());
		return new SerialLottoNumber(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
