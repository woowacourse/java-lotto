package domain;

import java.util.List;

public class Lotto {
	private final List<LottoNumber> lottoNumbers;

	public Lotto() {
		this.lottoNumbers = LottoGenerator.generateRandomLottoNumbers();
	}

	public int calculateInAnswerNumbers(AnswerLotto answerLotto) {
		return (int) this.lottoNumbers.stream().map(answerLotto::isInAnswerNumbers).count();
	}

	public boolean isHitBonusNumber(AnswerLotto answerLotto) {
		return this.lottoNumbers.stream().anyMatch(answerLotto::isSameWithBonusNumber);
	}
}
