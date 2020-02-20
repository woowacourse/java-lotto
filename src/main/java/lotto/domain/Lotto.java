package lotto.domain;

import java.util.List;

import lotto.validator.Validator;

public class Lotto {
	protected List<Integer> lottoNumbers;

	public Lotto(List<Integer> lottoNumbers) {
		Validator.validateLottoSize(lottoNumbers);
		this.lottoNumbers = lottoNumbers;
	}

	@Override
	public String toString() {
		return lottoNumbers.toString();
	}

	protected boolean isContain(int num) {
		return lottoNumbers.contains(num);
	}
}
