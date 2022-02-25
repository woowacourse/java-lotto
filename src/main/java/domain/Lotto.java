package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
	private final List<LottoNumber> lottoNumbers;

	public Lotto() {
		this.lottoNumbers = LottoGenerator.generateRandomLottoNumbers();
	}

	public int calculateInAnswerNumbers(AnswerLotto answerLotto) {
		return this.lottoNumbers
			.stream()
			.mapToInt(answerLotto::isInAnswerNumbers)
			.sum();
	}

	public boolean isHitBonusNumber(AnswerLotto answerLotto) {
		return this.lottoNumbers
			.stream()
			.anyMatch(answerLotto::isSameWithBonusNumber);
	}

	public List<Integer> getLottoNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (LottoNumber lottoNumber : this.lottoNumbers) {
			numbers.add(lottoNumber.getLottoNumber());
		}
		return numbers;
	}
}
