package lotto.domain.SerialLottoNumberFactory;

import lotto.domain.LottoNumber;
import lotto.domain.Lotto;

import java.util.List;

public abstract class AbstractLottoFactory implements LottoFactory {
	protected static final int ZERO_INDEX = 0;
	protected static final int SIX_INDEX = 6;

	private final List<LottoNumber> allLottoNumbers;

	AbstractLottoFactory() {
		allLottoNumbers = LottoNumber.allList();
	}

	protected List<LottoNumber> getAllLottoNumbers() {
		return allLottoNumbers;
	}

	@Override
	public abstract Lotto createLotto();
}
