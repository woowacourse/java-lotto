package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
	private static final int FIVE = 5;
	private static final String DELIMITER = ", ";
	private static final int LOTTO_LENGTH = 6;

	private final List<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validate(numbers);
		Collections.sort(numbers);
		this.numbers = numbers;
	}

	public Lotto(String input) {
		this(Arrays.stream(input.split(DELIMITER))
			.map(Integer::parseInt)
			.map(LottoNumber::new)
			.collect(Collectors.toList()));
	}

	private void validate(List<LottoNumber> numbers) {
		validateNumbersCount(numbers);
		validateNumbersDuplication(numbers);
	}

	private void validateNumbersCount(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_LENGTH) {
			throw new IllegalArgumentException("로또의 숫자는 6개여야 합니다.");
		}
	}

	private void validateNumbersDuplication(List<LottoNumber> numbers) {
		if (getDistinctCount(numbers) != numbers.size()) {
			throw new IllegalArgumentException("로또의 숫자는 중복될 수 없습니다.");
		}
	}

	private long getDistinctCount(List<LottoNumber> numbers) {
		return numbers.stream()
			.distinct()
			.count();
	}

	public List<LottoNumber> getNumbers() {
		return Collections.unmodifiableList(numbers);
	}

	public Rank compare(Lotto winningLotto, LottoNumber bonusNumber) {
		int count = (int)this.numbers.stream()
			.filter(winningLotto.numbers::contains)
			.count();

		if (count == FIVE && this.numbers.contains(bonusNumber)) {
			return Rank.SECOND;
		}

		return Rank.of(count);
	}
}