package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
	static final int CORRECT_SIZE = 6;

	private final Set<LottoNumber> lottoNumbers;

	public Lotto(Set<LottoNumber> lottoNumbers) {
		validateNull(lottoNumbers);
		validateSize(lottoNumbers);
		this.lottoNumbers = new TreeSet<>(lottoNumbers);
	}

	private void validateNull(Set<LottoNumber> lottoNumbers) {
		if (null == lottoNumbers) {
			throw new InvalidLottoException(InvalidLottoException.NULL);
		}
	}

	private void validateSize(Set<LottoNumber> lottoNumbers) {
		if (lottoNumbers.size() != CORRECT_SIZE) {
			throw new InvalidLottoException(InvalidLottoException.WRONG_SIZE);
		}
	}

	boolean contains(LottoNumber bonusNumber) {
		return lottoNumbers.contains(bonusNumber);
	}

	int getMatchCount(Lotto winningLotto) {
		List<LottoNumber> matchLottoNumbers = new ArrayList<>(lottoNumbers);
		matchLottoNumbers.retainAll(winningLotto.lottoNumbers);
		return matchLottoNumbers.size();
	}

	public Set<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}
}
