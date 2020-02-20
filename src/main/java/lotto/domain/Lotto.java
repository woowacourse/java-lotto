package lotto.domain;

import java.util.List;
import java.util.Objects;

import lotto.validator.Validator;

public class Lotto {
	protected List<LottoNo> lottoNumbers;

	public Lotto(List<LottoNo> lottoNumbers) {
		Validator.validateLottoSize(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	protected boolean isContain(LottoNo lottoNo) {
		return lottoNumbers.contains(lottoNo);
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
