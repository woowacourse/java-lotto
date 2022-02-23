package domain;

import java.util.List;

public class AnswerLotto {
	private AnswerLottoNumbers numbers;
	private BonusNumber bonusNumber;

	public AnswerLotto(AnswerLottoNumbers lottoNumbers, BonusNumber bonusNumber) {
		validateBonusNumberInNumbers(lottoNumbers, bonusNumber);
		this.numbers = lottoNumbers;
		this.bonusNumber = bonusNumber;
	}

	public List<Integer> getNumbers() {
		return this.numbers.getAnswerLottoNumbers();
	}

	public int getBonusNumber() {
		return this.bonusNumber.getNumber();
	}

	private void validateBonusNumberInNumbers(AnswerLottoNumbers numbers, BonusNumber bonusNumber) {
		if (numbers.isExists(bonusNumber)) {
			throw new IllegalArgumentException("[ERROR] 보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다");
		}
	}
}
