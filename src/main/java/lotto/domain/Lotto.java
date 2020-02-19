package lotto.domain;

import java.util.List;

import lotto.exceptions.LottoNumberDuplicatedException;
import lotto.exceptions.LottoNumberRangeException;

public class Lotto {
	private static final int MAX_LOTTO_NUMBER_RANGE = 45;
	private static final int MIN_LOTTO_NUMBER_RANGE = 1;
	private static final int LOTTO_NUMBER_SIZE = 6;
	private static final String LOTTO_NUMBER_RANGE_MESSAGE = "1~45 범위의 숫자만 로또 번호가 될 수 있습니다.";
	private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "잘못된 로또 번호입니다. 중복 안됨, 갯수는 6개";

	private List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		if (isInvalidNumberRange(numbers)) {
			throw new LottoNumberRangeException(LOTTO_NUMBER_RANGE_MESSAGE);
		}
		if (wrongAmountOfNumbers(numbers) || hasDuplicatedNumbers(numbers)) {
			throw new LottoNumberDuplicatedException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
		}
		this.numbers = numbers;
	}

	private boolean hasDuplicatedNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.stream().distinct().count();
	}

	private boolean wrongAmountOfNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.size();
	}

	private boolean isInvalidNumberRange(List<Integer> numbers) {
		return numbers.stream()
			.anyMatch(number -> number > MAX_LOTTO_NUMBER_RANGE || number < MIN_LOTTO_NUMBER_RANGE);
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
