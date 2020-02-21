package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import lotto.exception.DuplicateNumberException;
import lotto.exception.InvalidSizeException;

public class Lotto {
	private static final int LOTTO_SIZE = 6;

	protected final List<LottoNo> lottoNumbers;

	public Lotto(List<LottoNo> lottoNumbers) {
		validateLottoSize(lottoNumbers);
		validateDuplicateNumbers(lottoNumbers);
		this.lottoNumbers = new ArrayList<>(lottoNumbers);
	}

	private void validateLottoSize(List<LottoNo> lottoNumbers) {
		if (lottoNumbers.size() != LOTTO_SIZE) {
			throw new InvalidSizeException();
		}
	}

	private void validateDuplicateNumbers(List<LottoNo> lottoNumbers) {
		if (isDuplicated(lottoNumbers)) {
			throw new DuplicateNumberException();
		}
	}

	private boolean isDuplicated(List<LottoNo> lottoNumbers) {
		return new HashSet<>(lottoNumbers).size() != LOTTO_SIZE;
	}

	public boolean isContain(LottoNo lottoNo) {
		return lottoNumbers.contains(lottoNo);
	}

	public int compare(Lotto lotto) {
		return (int)this.lottoNumbers.stream()
			.filter(x -> lotto.isContain(x))
			.count();
	}

	@Override
	public String toString() {
		return lottoNumbers.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lotto lotto = (Lotto)o;
		return Objects.equals(lottoNumbers, lotto.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
