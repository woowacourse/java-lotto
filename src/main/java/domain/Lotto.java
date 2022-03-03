package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
	private final List<LottoNumber> lottoNumbers;

	public Lotto(List<LottoNumber> lottoNumbers) {
		this.lottoNumbers = lottoNumbers;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Lotto lotto = (Lotto) o;
		return Objects.equals(lottoNumbers, lotto.lottoNumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumbers);
	}
}
