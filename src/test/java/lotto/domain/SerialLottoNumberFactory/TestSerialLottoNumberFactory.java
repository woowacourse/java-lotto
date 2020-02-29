package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.SerialLottoNumber;

public class TestSerialLottoNumberFactory extends AbstractSerialLottoNumberFactory {

	@Override
	public SerialLottoNumber createSerialLottoNumber() {
		return new SerialLottoNumber(super.getAllLottoNumbers().subList(ZERO_INDEX, SIX_INDEX));
	}
}
