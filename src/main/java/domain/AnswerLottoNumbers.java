package domain;

import java.util.HashSet;
import java.util.List;

import static constant.LottoConstant.*;

public class AnswerLottoNumbers {

	private static final String NUMBER_IN_RANGE = "[ERROR] 1이상 45 이하의 숫자만 허용됩니다.";
	private static final String NUMBER_OF_NUMBERS_IS_NOT_CORRECT = "[ERROR] " + NUMBER_OF_NUMBERS + "개의 숫자만 허용됩니다.";
	private static final String NUMBERS_MUST_NOT_DUPLICATED = "[ERROR] 지난 주 당첨 번호 숫자들은 중복일 수 없습니다.";

	private final List<Integer> numbers;

	public AnswerLottoNumbers(List<Integer> numbers) {
		validateAllCondition(numbers);
		this.numbers = numbers;
	}

	public boolean isExists(int target) {
		return this.numbers.contains(target);
	}

	public List<Integer> getNumbers() {
		return this.numbers;
	}

	private void validateAllCondition(List<Integer> numbers) {
		validateLength(numbers);
		validateNumbers(numbers);
		validateDuplicateInNumbers(numbers);
	}

	private void validateNumbers(List<Integer> numbers) {
		int upperCount = (int) numbers.stream().filter(number -> number > MAX_NUMBER).count();
		int lowerCount = (int) numbers.stream().filter(number -> number < MIN_NUMBER).count();

		if (upperCount > 0 || lowerCount > 0) {
			throw new IllegalArgumentException(NUMBER_IN_RANGE);
		}
	}

	private void validateLength(List<Integer> numbers) {
		if (numbers.size() != NUMBER_OF_NUMBERS) {
			throw new IllegalArgumentException(NUMBER_OF_NUMBERS_IS_NOT_CORRECT);
		}
	}

	private void validateDuplicateInNumbers(List<Integer> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException(NUMBERS_MUST_NOT_DUPLICATED);
		}
	}
}


