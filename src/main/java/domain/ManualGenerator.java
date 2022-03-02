package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManualGenerator implements LottoGenerator {
	private static final int LOWER_BOUND = 1;
	private static final int UPPER_BOUND = 45;
	private static final String ERROR_NOT_IN_RANGE = "[ERROR] 1이상 45 이하의 숫자만 허용됩니다.";
	private static final String ERROR_NOT_CORRECT_SIZE = "[ERROR] 지난 주 당첨 번호는 반드시 6개여야 합니다.";
	private static final String ERROR_DUPLICATE_DETECT = "[ERROR] 지난 주 당첨 번호는 중복일 수 없습니다.";
	private final List<Integer> candidates;

	public ManualGenerator(List<Integer> userInput) {
		this.candidates = userInput;
	}

	@Override
	public List<LottoNumber> generateLottoNumbers() {
		validateSizeSix(this.candidates);
		validateInRange(this.candidates);
		validateDuplicate(this.candidates);
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		for (int eachNum : this.candidates) {
			lottoNumbers.add(new LottoNumber(eachNum));
		}
		return lottoNumbers;
	}

	private void validateInRange(List<Integer> userInput) {
		boolean range = userInput.stream()
			.anyMatch(num -> num < LOWER_BOUND || num > UPPER_BOUND);
		if (range) {
			throw new IllegalArgumentException(ERROR_NOT_IN_RANGE);
		}
	}

	private void validateSizeSix(List<Integer> userInput) {
		if (userInput.size() != 6) {
			throw new IllegalArgumentException(ERROR_NOT_CORRECT_SIZE);
		}
	}

	private void validateDuplicate(List<Integer> userInput) {
		if (userInput.size() != new HashSet<>(userInput).size()) {
			throw new IllegalArgumentException(ERROR_DUPLICATE_DETECT);
		}
	}
}
