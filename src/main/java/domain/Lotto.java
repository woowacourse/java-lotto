package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.LottoLengthException;
import exception.LottoNumberDuplicateException;

public class Lotto {
	private static final int LOTTO_LENGTH = 6;

	private final Set<LottoNumber> numbers;

	public Lotto(List<LottoNumber> numbers) {
		validateLength(numbers);
		this.numbers = new HashSet<>(numbers);
		validateDuplication(this.numbers);
	}

	private void validateLength(List<LottoNumber> numbers) {
		if (numbers.size() != LOTTO_LENGTH) {
			throw new LottoLengthException();
		}
	}

	private void validateDuplication(Set<LottoNumber> numbers) {
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

		boolean bonusNumberMatch = numbers.stream()
			.anyMatch(lottoNumber -> lottoNumber == bonusNumber);

		return Rank.of(count, bonusNumberMatch);
	}
}