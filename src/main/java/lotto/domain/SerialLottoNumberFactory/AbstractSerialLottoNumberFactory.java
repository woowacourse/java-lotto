package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.AllLottoNumbers;
import lotto.domain.LottoNumber;
import lotto.domain.SerialLottoNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSerialLottoNumberFactory implements SerialLottoNumberFactory {
	protected static final int ZERO_INDEX = 0;
	protected static final int SIX_INDEX = 6;

	private final List<LottoNumber> allLottoNumbers;

	AbstractSerialLottoNumberFactory() {
		allLottoNumbers = new ArrayList<>(AllLottoNumbers.getAll());
	}

	protected List<LottoNumber> getAllLottoNumbers() {
		return allLottoNumbers;
	}

	@Override
	public abstract SerialLottoNumber createSerialLottoNumber();
}
