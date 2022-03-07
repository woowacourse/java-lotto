package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnswerLotto {
	private static final String BONUS_AND_ANSWER_MUST_NOT_DUPLICATED = "보너스 번호는 지난 주 당첨 번호 숫자들과 중복일 수 없습니다";

	private final Set<LottoNumber> numbers;
	private final LottoNumber bonusNumber;

	private AnswerLotto(Set<LottoNumber> numbers, LottoNumber bonusNumber) {
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	public static AnswerLotto of(List<Integer> inputNumbers, int inputBonus) {
		Set<LottoNumber> numbers
			= inputNumbers.stream()
			.map(l -> LottoNumber.of(l))
			.collect(Collectors.toSet());
		LottoNumber bonusNumber = LottoNumber.of(inputBonus);

		validateDuplication(numbers, bonusNumber);

		return new AnswerLotto(numbers, bonusNumber);
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}

	private static void validateDuplication(Set<LottoNumber> numbers, LottoNumber bonusNumber) {
		if (numbers.contains(bonusNumber)) {
			throw new IllegalArgumentException(BONUS_AND_ANSWER_MUST_NOT_DUPLICATED);
		}
	}

}
