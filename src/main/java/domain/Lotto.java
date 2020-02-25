package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import exception.LottoLengthException;
import exception.LottoNumberDuplicateException;

public class Lotto {
	private static final int LOTTO_NUMBER_FIVE_MATCHED = 5;
	private static final String DELIMITER = ", ";
	private static final int LOTTO_LENGTH = 6;

	private final Set<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		lengthValidate(numbers);
		this.numbers = new HashSet<>(numbers);
		duplicationValidate(this.numbers);
	}

	public Lotto(String input) {
		this(Arrays.stream(input.split(DELIMITER))
			.map(Integer::parseInt)
			.map(LottoNumber::createNumber)
			.collect(Collectors.toList()));
	}

	private void lengthValidate(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_LENGTH) {
			throw new LottoLengthException();
		}
	}

	private void duplicationValidate(Set<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_LENGTH) {
			throw new LottoNumberDuplicateException();
		}
	}

	public List<LottoNumber> getNumbers() {
		List<LottoNumber> numbers = new ArrayList<>(this.numbers);
		Collections.sort(numbers);
		return numbers;
	}

	public boolean isContains(LottoNumber lottoNumber) {
		return numbers.contains(lottoNumber);
	}

	public Rank compare(Lotto winningLotto, LottoNumber bonusNumber) {
		int count = (int)this.numbers.stream()
			.filter(winningLotto.numbers::contains)
			.count();

		if (count == LOTTO_NUMBER_FIVE_MATCHED && this.numbers.contains(bonusNumber)) {
			return Rank.SECOND;
		}

		return Rank.of(count);
	}
}