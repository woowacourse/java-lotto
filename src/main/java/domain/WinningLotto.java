package domain;

import java.util.List;
import java.util.stream.Collectors;

import exception.LottoNumberDuplicateException;

public class WinningLotto {
	private Lotto winningNumbers;
	private LottoNumber bonusNumber;

	public WinningLotto(List<Integer> inputSixNumbers, int inputBonusNumber) {
		validateDuplication(inputSixNumbers, inputBonusNumber);
		this.winningNumbers = new Lotto(inputSixNumbers
			.stream()
			.map(LottoNumber::createNumber)
			.collect(Collectors.toList()));
		this.bonusNumber = LottoNumber.createNumber(inputBonusNumber);
	}

	private void validateDuplication(List<Integer> inputSixNumbers, int inputBonusNumber) {
		if (inputSixNumbers.contains(inputBonusNumber)) {
			throw new LottoNumberDuplicateException();
		}
	}

	public boolean isNumberMatch(LottoNumber lottoNumber) {
		return winningNumbers.isContains(lottoNumber);
	}

	public boolean isBonusMatch(LottoNumber lottoNumber) {
		return bonusNumber.equals(lottoNumber);
	}
}
