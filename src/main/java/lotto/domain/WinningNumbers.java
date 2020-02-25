package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WinningNumbers {
	private static final String DELIMITER = ", ";
	private static final int WINNING_NUMBER_SIZE = 6;

	private final LottoTicket winningTicket;
	private final LottoNumber bonusNumber;

	public WinningNumbers(String winningTicket, String bonusNumber) {
		final List<String> winningNumbersValues = Arrays.asList(winningTicket.split(DELIMITER));
		checkValidationOf(winningNumbersValues, bonusNumber);

		this.winningTicket = winningNumbersValues.stream()
			.mapToInt(Integer::parseInt)
			.mapToObj(LottoNumber::new)
			.collect(Collectors.collectingAndThen(toList(), LottoTicket::new));

		this.bonusNumber = new LottoNumber(Integer.parseInt(bonusNumber));
	}

	private void checkValidationOf(final List<String> winningNumbersValue, final String bonusNumber) {
		checkValid(winningNumbersValue);
		checkValid(winningNumbersValue, bonusNumber);
	}

	private void checkValid(final List<String> winningNumbersValue) {
		if (isNotMatchSize(winningNumbersValue)) {
			throw new IllegalArgumentException("당첨 번호는 보너스 번호를 제외하고 6자리 이어야 합니다");
		}
		if (isNotNumberFormat(winningNumbersValue)) {
			throw new IllegalArgumentException("당첨 번호는 정수만 가능합니다");
		}
		if (isDuplicatedNumber(winningNumbersValue)) {
			throw new IllegalArgumentException("중복된 번호는 허용하지 않습니다");
		}
	}

	private void checkValid(final List<String> winningNumbersValue, final String bonusNumber) {
		if (winningNumbersValue.contains(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호 일반 당첨번호와 중복될수 없습니다.");
		}
		if (isNotDigit().test(bonusNumber)) {
			throw new IllegalArgumentException("보너스 번호는 정수만 가능합니다");
		}
	}

	private boolean isDuplicatedNumber(final List<String> winningNumbersValue) {
		final HashSet<String> checkDuplicateSet = new HashSet<>(winningNumbersValue);
		return isNotMatchSize(new ArrayList<>(checkDuplicateSet));
	}

	private boolean isNotNumberFormat(final List<String> winningNumbersValue) {
		return winningNumbersValue.stream()
			.anyMatch(isNotDigit());
	}

	private Predicate<String> isNotDigit() {
		return str -> str.chars()
			.anyMatch(ch -> !Character.isDigit(ch));
	}

	private boolean isNotMatchSize(final List<String> winningNumbersValue) {
		return winningNumbersValue.size() != WINNING_NUMBER_SIZE;
	}

	public LottoTicket getWinningTicket() {
		return winningTicket;
	}

	public boolean isMatchWithBonus(final LottoNumber lottoNumber) {
		return lottoNumber.equals(bonusNumber);
	}
}
