package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import lotto.exceptions.LottoNumberDuplicatedException;

public class Lotto {
	private static final int LOTTO_NUMBER_SIZE = 6;
	private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "잘못된 로또 번호입니다. 중복 안됨, 갯수는 6개";

	private List<LottoNumber> numbers;

	public Lotto(List<Integer> numbers) {
		if (wrongAmountOfNumbers(numbers) || hasDuplicatedNumbers(numbers)) {
			throw new LottoNumberDuplicatedException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
		}

		this.numbers = numbers.stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList());
	}

	private boolean hasDuplicatedNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.stream().distinct().count();
	}

	private boolean wrongAmountOfNumbers(List<Integer> numbers) {
		return LOTTO_NUMBER_SIZE != numbers.size();
	}

	public int containCount(Lotto lotto) {
		return (int)numbers.stream()
			.filter(lotto::contains)
			.count();
	}

	public boolean contains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	public List<Integer> getNumbers() {
		return numbers.stream()
			.map(LottoNumber::getNumber)
			.collect(Collectors.toList());
	}
}
