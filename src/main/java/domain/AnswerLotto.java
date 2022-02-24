package domain;

import java.util.Arrays;
import java.util.List;

public class AnswerLotto {
	private final List<LottoNumber> answerNumbers;
	private final LottoNumber bonusLottoNumber;
	private final String ERROR_DUPLICATE_WITH_BONUS_NUMBER_IN_ANSWER_NUMBERS = "[ERROR] 보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다";

	public AnswerLotto(String[] userInput, LottoNumber bonusLottoNumber) {
		this.answerNumbers = LottoGenerator.generateAnswerLottoNumbers(userInput);
		this.bonusLottoNumber = bonusLottoNumber;
		validateBonusNumberInNumbers(userInput, bonusLottoNumber);
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

	private void validateBonusNumberInNumbers(String[] userInput, LottoNumber bonusLottoNumber) {
		if (Arrays.stream(userInput).anyMatch(num -> num.equals(String.valueOf(bonusLottoNumber.getLottoNumber())))) {
			throw new IllegalArgumentException(ERROR_DUPLICATE_WITH_BONUS_NUMBER_IN_ANSWER_NUMBERS);
		}
	}
}
