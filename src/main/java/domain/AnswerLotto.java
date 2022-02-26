package domain;

import java.util.List;

public class AnswerLotto {
	private static final String ERROR_DUPLICATE_WITH_BONUS_NUMBER_IN_ANSWER_NUMBERS = "[ERROR] 보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다";
	private final List<LottoNumber> answerNumbers;
	private final LottoNumber bonusLottoNumber;

	public AnswerLotto(List<Integer> answerNumbers, LottoNumber bonusLottoNumber) {
		this.answerNumbers = LottoGenerator.generateAnswerLottoNumbers(answerNumbers);
		this.bonusLottoNumber = bonusLottoNumber;
		validateBonusNumberInNumbers();
	}

	public int isInAnswerNumbers(LottoNumber lottoNumber) {
		if (this.answerNumbers.contains(lottoNumber)) {
			return 1;
		}
		return 0;
	}

	public boolean isSameWithBonusNumber(LottoNumber lottoNumber) {
		return this.bonusLottoNumber.equals(lottoNumber);
	}

	private void validateBonusNumberInNumbers() {
		if (this.answerNumbers.contains(this.bonusLottoNumber)) {
			throw new IllegalArgumentException(ERROR_DUPLICATE_WITH_BONUS_NUMBER_IN_ANSWER_NUMBERS);
		}
	}
}
