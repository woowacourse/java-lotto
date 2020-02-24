package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;

import java.util.HashSet;
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

	// TODO: 2020-02-24 is없애는 게 관례상 더 맞다 
	public boolean isContains(LottoNumber bonusNumber) {
		return lottoNumbers.contains(bonusNumber);
	}

	public int calculateMatchCount(Lotto winningLotto) {
		Set<LottoNumber> matchLottoNumbers = new HashSet<>(lottoNumbers);
		matchLottoNumbers.retainAll(winningLotto.lottoNumbers);
		return matchLottoNumbers.size();
	}

	public Set<LottoNumber> getLottoNumbers() {
		return lottoNumbers;
	}
}
