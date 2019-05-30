package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.woowacourse.lotto.exception.InvalidNumberException;
import static com.woowacourse.lotto.domain.LottoNumber.*;


public class WinningLotto {
	private static final String REGEX_OF_NUMBER = "[1-9]|[1-3][0-9]|[4][0-5]";
	private static final Pattern PATTERN_OF_LOTTO_NUMBER = Pattern.compile(REGEX_OF_NUMBER);
	private Lotto numbers;
	private BonusBall bonusBall;

	public WinningLotto(List<String> numbers, BonusBall bonusBall) {
		this.numbers = new Lotto(numbers.stream().map(Integer::new).collect(Collectors.toList()));
		this.bonusBall = bonusBall;
		validateDuplicateBonusBall();
		validateDuplicateNumber(numbers);
		validateSize(numbers);
		validateNumber(numbers);
	}

	private void validateDuplicateBonusBall() {
		if(numbers.contains(bonusBall.getBonusBallNumber())) {
			throw new IllegalArgumentException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	private void validateDuplicateNumber(List<String> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new InvalidNumberException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	private void validateSize(List<String> numbers) {
		if (numbers.size() != NUMBER_OF_LOTTO) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	private void validateNumber(List<String> numbers) {
		if (!numbers.stream().allMatch(number -> PATTERN_OF_LOTTO_NUMBER.matcher(number).matches())) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	public int matchLotto(Lotto lotto) {
		return numbers.getCountOfMatchedNumber(lotto);
	}

	public boolean matchBonusBall(Lotto lotto) {
		return lotto.contains(bonusBall.getBonusBallNumber());
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
		return Objects.equals(numbers, that.numbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numbers);
	}
}
