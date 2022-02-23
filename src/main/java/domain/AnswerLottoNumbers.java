package domain;

import java.util.HashSet;
import java.util.List;

public class AnswerLottoNumbers {
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
		if (numbers.size() != new HashSet<Integer>(numbers).size()) {
			throw new IllegalArgumentException("[ERROR] 지난 주 당첨 번호 숫자들은 중복일 수 없습니다.");
		}
	}

	private void validateNumbers(List<Integer> numbers) {
		int upperCount = (int) numbers.stream().filter(number -> number > 45).count();
		int lowerCount = (int) numbers.stream().filter(number -> number < 1).count();

		if (upperCount > 0 || lowerCount > 0) {
			throw new IllegalArgumentException("[ERROR] 1이상 45 이하의 숫자만 허용됩니다.");
		}
	}

	private void validateLength(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("[ERROR] 6개의 숫자만 허용됩니다.");
		}
	}

	public List<Integer> getAnswerLottoNumbers() {
		return this.lottoNumbers;
	}

	public boolean isExists(BonusNumber bonusNumber) {
		return this.lottoNumbers.contains(bonusNumber.getNumber());
	}
}
