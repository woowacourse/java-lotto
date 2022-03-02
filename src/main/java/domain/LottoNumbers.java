package domain;

import java.util.*;

import static constant.LottoConstant.NUMBER_OF_NUMBERS;

public class LottoNumbers {

	private static final String NUMBER_OF_NUMBERS_IS_NOT_CORRECT = NUMBER_OF_NUMBERS + "개의 숫자만 허용됩니다.";
	private static final String NUMBERS_MUST_NOT_DUPLICATED = "숫자들은 중복일 수 없습니다.";

	private final Set<LottoNumber> numbers;

	public LottoNumbers(List<Integer> input) {
		Set<LottoNumber> numbers = new HashSet<>();

		for (int i : input) {
			numbers.add(new LottoNumber(i));
		}

		validateNumbers(input.size(), numbers.size());

		this.numbers = numbers;
	}

	public boolean isExist(LottoNumber number) {
		return numbers.contains(number);
	}

	public int calculateDuplicatedCount(Set<LottoNumber> target) {
		return (int) target.stream().filter(t -> isExist(t)).count();
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	private static void validateNumbers(int input_size, int numbers_size) {
		validateLength(input_size);
		validateDuplication(input_size, numbers_size);
	}

	private static void validateLength(int input_size) {
		if (input_size != NUMBER_OF_NUMBERS) {
			throw new IllegalArgumentException(NUMBER_OF_NUMBERS_IS_NOT_CORRECT);
		}
	}

	private static void validateDuplication(int input_size, int numbers_size) {
		if (input_size != numbers_size) {
			throw new IllegalArgumentException(NUMBERS_MUST_NOT_DUPLICATED);
		}
	}

}
