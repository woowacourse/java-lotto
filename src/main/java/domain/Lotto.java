package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
	private static final int FIVE = 5;
	private static final String DELIMITER = ", ";
	private static final int LOTTO_LENGTH = 6;

	private final Set<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		this.numbers = new HashSet<>(numbers);
		duplicationValidate(this.numbers);
	}

	public Lotto(String input) {
		this(Arrays.stream(input.split(DELIMITER))
			.map(Integer::parseInt)
			.map(LottoNumber::get)
			.collect(Collectors.toList()));
	}

	private void duplicationValidate(Set<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_LENGTH) {
			throw new IllegalArgumentException("로또 번호가 중복되거나, 6개를 입력하지 않았습니다.");
		}
	}

	public Set<LottoNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
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