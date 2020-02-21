package lotto.domain.Lotto;

import lotto.domain.LottoNumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
	static final int CORRECT_SIZE = 6;

	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		validateNull(lottoNumbers);
		validateSize(lottoNumbers);
		validateDuplication(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	private void validateNull(List<LottoNumber> lottoNumbers) {
		if (null == lottoNumbers) {
			throw new InvalidLottoException(InvalidLottoException.NULL);
		}
	}

	private void validateSize(List<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != CORRECT_SIZE) {
			throw new InvalidLottoException(InvalidLottoException.WRONG_SIZE);
		}
	}

	private void validateDuplication(List<LottoNumber> lottoNumbers) {
		long distinctSize = lottoNumbers.stream()
				.distinct()
				.count();

		if (distinctSize != lottoNumbers.size()) {
			throw new InvalidLottoException(InvalidLottoException.DUPLICATION);
		}
	}

	public boolean isContains(LottoNumber bonusNumber) {
		return lottoNumbers.contains(bonusNumber);
	}

	public int getMatchCount(Lotto winningLotto) {
		List<LottoNumber> matchLottoNumbers = new ArrayList<>(lottoNumbers);
		matchLottoNumbers.retainAll(winningLotto.lottoNumbers);
		return matchLottoNumbers.size();
	}

	public List<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}
}
