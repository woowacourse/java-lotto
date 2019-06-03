package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.woowacourse.lotto.exception.InvalidNumberException;
import org.apache.commons.lang3.StringUtils;

import static com.woowacourse.lotto.domain.LottoNumber.*;


public class WinningLotto {
	//private static final String REGEX_OF_NUMBER = "[1-9]|[1-3][0-9]|4[0-5]";
	//private static final Pattern PATTERN_OF_LOTTO_NUMBER = Pattern.compile(REGEX_OF_NUMBER);
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
			throw new IllegalArgumentException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	private void validateDuplicatedNumber(List<String> numbers) {
		if (numbers.size() != new HashSet<>(numbers).size()) {
			throw new InvalidNumberException(ExceptionOutput.DUPLICATE_LOTTO_NUMBER.getExceptionMessage());
		}
	}

	private void validateSize(List<String> numbers) {
		if (numbers.size() != NUMBER_OF_LOTTO) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	private void validateRangeOfNumbers(List<String> numbers) {
		if(!numbers.stream().allMatch(number -> checkRangeOfNumber(Integer.parseInt(number)))) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
	}

	private boolean checkRangeOfNumber(int number) {
		return (MIN_NUMBER_OF_LOTTO <= number && number <= MAX_NUMBER_OF_LOTTO);
	}

	private void validateTypeOfNumbers(List<String> numbers) {
		if(!numbers.stream().allMatch(number -> StringUtils.isNumeric(number))) {
			throw new IllegalArgumentException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
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
