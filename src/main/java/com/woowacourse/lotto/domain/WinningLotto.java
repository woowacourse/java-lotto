package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.apache.commons.lang3.StringUtils;

import static com.woowacourse.lotto.domain.Lotto.DUPLICATE_LOTTO_NUMBER;
import static com.woowacourse.lotto.domain.LottoNumber.*;


public class WinningLotto {
	private Lotto winningLotto;
	private LottoNumber bonusBall;

	public WinningLotto(final List<String> numbers, int bonusBall) {
		List<LottoNumber> lottoNumbers = numbers.stream()
				.map(number -> getLottoNumber(Integer.valueOf(number)))
				.collect(Collectors.toList());
		this.winningLotto = new Lotto(lottoNumbers);
		validateDuplicateBonusBall(bonusBall);
		this.bonusBall = LottoNumber.getLottoNumber(bonusBall);
		validateDuplicatedNumber(numbers);
		validateSize(numbers);
		validateRangeOfNumbers(numbers);
		validateTypeOfNumbers(numbers);
	}

	private void validateDuplicateBonusBall(int bonusBall) {
		if (winningLotto.contains(LottoNumber.getLottoNumber(bonusBall))) {
			throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBER);
		}
	}

	private void validateDuplicatedNumber(List<String> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new InvalidNumberException(DUPLICATE_LOTTO_NUMBER);
		}
	}

	private void validateSize(List<String> numbers) {
		if (numbers.size() != NUMBER_OF_LOTTO) {
			throw new InvalidNumberException(VIOLATE_LOTTO_NUMBER_RANGE);
		}
	}

	private void validateRangeOfNumbers(List<String> numbers) {
		if(!numbers.stream().allMatch(number -> checkRangeOfNumber(Integer.parseInt(number)))) {
			throw new InvalidNumberException(VIOLATE_LOTTO_NUMBER_RANGE);
		}
	}

	private boolean checkRangeOfNumber(int number) {
		return (MIN_NUMBER_OF_LOTTO <= number && number <= MAX_NUMBER_OF_LOTTO);
	}

	private void validateTypeOfNumbers(List<String> numbers) {
		if(!numbers.stream().allMatch(number -> StringUtils.isNumeric(number))) {
			throw new IllegalArgumentException(VIOLATE_LOTTO_NUMBER_RANGE);
		}
	}

	public int matchLotto(Lotto lotto) {
		return winningLotto.getCountOfMatchedNumber(lotto);
	}

	public boolean matchBonusBall(Lotto lotto) {
		return lotto.contains(bonusBall);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof WinningLotto)) {
			return false;
		}
		final WinningLotto that = (WinningLotto) o;
		return Objects.equals(winningLotto, that.winningLotto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(winningLotto);
	}
}
