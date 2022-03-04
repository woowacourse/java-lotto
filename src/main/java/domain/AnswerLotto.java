package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AnswerLotto {
	private static final String BONUS_AND_ANSWER_MUST_NOT_DUPLICATED = "보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다";

	private final LottoNumbers numbers;
	private final LottoNumber bonusNumber;

	private AnswerLotto(LottoNumbers numbers, LottoNumber bonusNumber) {
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	public static AnswerLotto of(List<Integer> inputNumbers, int inputBonus) {
		LottoNumbers numbers = new LottoNumbers(new ArrayList<>(inputNumbers));
		LottoNumber bonusNumber = new LottoNumber(inputBonus);

		if (numbers.isExist(bonusNumber)) {
			throw new IllegalArgumentException(BONUS_AND_ANSWER_MUST_NOT_DUPLICATED);
		}

		return new AnswerLotto(numbers, bonusNumber);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers.getNumbers());
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}

}
