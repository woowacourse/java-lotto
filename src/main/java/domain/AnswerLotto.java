package domain;

import java.util.List;

public class AnswerLotto {

	private static final String BONUS_AND_ANSWER_MUST_NOT_DUPLICATED = "[ERROR] 보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다";

	private final AnswerLottoNumbers numbers;
	private final BonusNumber bonusNumber;

	public AnswerLotto(AnswerLottoNumbers lottoNumbers, BonusNumber bonusNumber) {
		validateBonusNumberInNumbers(lottoNumbers, bonusNumber);
		this.numbers = lottoNumbers;
		this.bonusNumber = bonusNumber;
	}

	public List<Integer> getNumbers() {
		return this.numbers.getNumbers();
	}

	public int getBonusNumber() {
		return this.bonusNumber.getNumber();
	}

	private void validateBonusNumberInNumbers(AnswerLottoNumbers numbers, BonusNumber bonusNumber) {
		if (numbers.isExists(bonusNumber.getNumber())) {
			throw new IllegalArgumentException(BONUS_AND_ANSWER_MUST_NOT_DUPLICATED);
		}
	}
}
