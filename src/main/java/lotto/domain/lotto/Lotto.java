package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lotto.domain.exception.InvalidLottoException;

public class Lotto {
	private static final String DELIMITER = ",";
	public static final int CORRECT_SIZE = 6;

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

	public boolean contains(LottoNumber bonusNumber) {
		return lottoNumbers.contains(bonusNumber);
	}

	public int getMatchCount(Lotto winningLotto) {
		List<LottoNumber> matchLottoNumbers = new ArrayList<>(lottoNumbers);
		matchLottoNumbers.retainAll(winningLotto.lottoNumbers);
		return matchLottoNumbers.size();
	}

	@Override
	public String toString() {
		return "[" + lottoNumbers.stream()
			.map(LottoNumber::toString)
			.collect(Collectors.joining(DELIMITER)) + "]";
	}
}
