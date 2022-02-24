package domain;

import java.util.HashSet;
import java.util.List;

public class AnswerLottoNumbers {
	private static final String MUST_NOT_DUPLICATED = "[ERROR] 지난 주 당첨 번호 숫자들은 중복일 수 없습니다.";
	private static final String NUMBER_IN_RANGE = "[ERROR] 1이상 45 이하의 숫자만 허용됩니다.";
	private static final String COUNT_MUST_BE_SIX = "[ERROR] 6개의 숫자만 허용됩니다.";

	private final List<Integer> lottoNumbers;

	public AnswerLottoNumbers(List<Integer> userInput) {
		validateInput(userInput);
		this.lottoNumbers = userInput;
	}

	private void validateInput(List<Integer> numbers) {
		validateLength(numbers);
		validateNumbers(numbers);
		validateDuplicateInNumbers(numbers);
	}

	private void validateDuplicateInNumbers(List<Integer> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException(MUST_NOT_DUPLICATED);
		}
	}

	private void validateNumbers(List<Integer> numbers) {
		int upperCount = (int) numbers.stream().filter(number -> number > 45).count();
		int lowerCount = (int) numbers.stream().filter(number -> number < 1).count();

		if (upperCount > 0 || lowerCount > 0) {
			throw new IllegalArgumentException(NUMBER_IN_RANGE);
		}
	}

	private void validateLength(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException(COUNT_MUST_BE_SIX);
		}
	}

	public List<Integer> getAnswerLottoNumbers() {
		return this.lottoNumbers;
	}

	public boolean isExists(BonusNumber bonusNumber) {
		return this.lottoNumbers.contains(bonusNumber.getNumber());
	}
}
