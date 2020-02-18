package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoNumbers {
	private static final int SIZE = 6;
	private static final String DUPLICATED_NUMBER_MESSAGE = "로또 번호가 중복됩니다.";
	private static final String INVALID_SIZE_MESSAGE = "로또 번호가 존재하지 않습니다.";

	private final List<LottoNumber> numbers = new ArrayList<>();

	public LottoNumbers(List<LottoNumber> numbers) {
		validate(numbers);
		this.numbers.addAll(numbers);
		Collections.sort(this.numbers);
	}

	private void validate(List<LottoNumber> numbers) {
		validateSize(numbers);
		validateDuplicate(numbers);
	}

	private void validateSize(List<LottoNumber> numbers) {
		if (Objects.isNull(numbers) || numbers.size() != SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
		}
	}

	private void validateDuplicate(List<LottoNumber> numbers) {
		Set<LottoNumber> distinctNumbers = new HashSet<>(numbers);
		if (distinctNumbers.size() != numbers.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_MESSAGE);
		}
	}

	public List<LottoNumber> getNumbers() {
		return Collections.unmodifiableList(numbers);
	}

	public boolean contains(LottoNumber number) {
		return numbers.contains(number);
	}

	public int calculateMatchCount(LottoNumbers target) {
		return (int)numbers.stream()
				.filter(target::contains)
				.count();
	}
}
