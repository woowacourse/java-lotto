package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static constant.ErrorConstant.START_ERROR;
import static constant.LottoConstant.NUMBER_OF_NUMBERS;

public class LottoNumbers {

	private static final String NUMBER_OF_NUMBERS_IS_NOT_CORRECT = START_ERROR + NUMBER_OF_NUMBERS + "개의 숫자만 허용됩니다.";
	private static final String NUMBERS_MUST_NOT_DUPLICATED = START_ERROR + "숫자들은 중복일 수 없습니다.";

	private final List<LottoNumber> numbers;

	public LottoNumbers(List<LottoNumber> numbers) {
		validateNumbers(numbers);
		this.numbers = numbers;
	}

	public static LottoNumbers of(List<Integer> input) {
		List<LottoNumber> numbers = new ArrayList<>();

		for (int i : input) {
			numbers.add(new LottoNumber(i));
		}

		return new LottoNumbers(numbers);
	}

	public boolean isExist(LottoNumber number) {
		return numbers.contains(number);
	}

	public int calculateDuplicatedCount(List<LottoNumber> target) {
		return (int) numbers.stream().filter(n -> target.contains(n)).count();
	}

	public List<LottoNumber> getNumbers() {
		return Collections.unmodifiableList(this.numbers);
	}

	private void validateNumbers(List<LottoNumber> numbers) {
		validateLength(numbers);
		validateDuplicateInNumbers(numbers);
	}

	private void validateLength(List<LottoNumber> numbers) {
		if (numbers.size() != NUMBER_OF_NUMBERS) {
			throw new IllegalArgumentException(NUMBER_OF_NUMBERS_IS_NOT_CORRECT);
		}
	}

	private void validateDuplicateInNumbers(List<LottoNumber> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new IllegalArgumentException(NUMBERS_MUST_NOT_DUPLICATED);
		}
	}

}
