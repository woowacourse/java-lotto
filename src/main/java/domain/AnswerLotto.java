package domain;

import java.util.List;

public class AnswerLotto {
	private List<Integer> numbers;
	private int bonusNumber;

	public AnswerLotto(List<Integer> numbers, int bonusNumber) {
		validateInput(numbers, bonusNumber);
		this.numbers = numbers;
		this.bonusNumber = bonusNumber;
	}

	private void validateInput(List<Integer> numbers, int bonusNumber) {
		validateLength(numbers);
		validateNumbers(numbers);
		validateBonusNumber(bonusNumber);
	}

	private void validateLength(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("[ERROR] 6개의 숫자만 허용됩니다.");
		}

	}

	private void validateNumbers(List<Integer> numbers) {
		int upperCount = (int) numbers.stream().filter(number -> number > 45).count();
		int lowerCount = (int) numbers.stream().filter(number -> number < 1).count();


		if (upperCount > 0 || lowerCount > 0) {
			throw new IllegalArgumentException("[ERROR] 1이상 45 이하의 숫자만 허용됩니다.");
		}
	}

	private void validateBonusNumber(int bonusNumber) {
		if (bonusNumber < 1 || bonusNumber > 45) {
			throw new IllegalArgumentException("[ERROR] 1이상 45 이하의 숫자만 허용됩니다.");
		}
	}

}
