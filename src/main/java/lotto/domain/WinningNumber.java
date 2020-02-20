package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private static final int LOTTO_SIZE = 6;
	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;

	private final List<Integer> winningNumber;
	private final int bonusNumber;

	public WinningNumber(final List<String> winningNumber, final int bonusNumber) {
		validateWinningNumber(winningNumber);
		this.winningNumber = winningNumber.stream()
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		validateBonusNumber(bonusNumber);
		this.bonusNumber = bonusNumber;
	}

	private void validateWinningNumber(List<String> winningNumber) {
		validateNullAndEmpty(winningNumber);
		validateNumberAmount(winningNumber);
		validateDuplicate(winningNumber);
		validateRange(winningNumber);
	}

	private void validateNullAndEmpty(List<String> winningNumber) {
		if (winningNumber == null || winningNumber.isEmpty()) {
			throw new IllegalArgumentException("널이나 빈 값이 들어올 수 없습니다.");
		}
	}

	private void validateNumberAmount(List<String> winningNumber) {
		if (winningNumber.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException(String.format("로또 변호는 %s개여야 합니다.", LOTTO_SIZE));
		}
	}

	private void validateDuplicate(List<String> winningNumber) {
		if (winningNumber.size() != new HashSet<>(winningNumber).size()) {
			throw new IllegalArgumentException("중복된 번호가 있습니다.");
		}
	}

	private void validateRange(List<String> winningNumber) {
		if (winningNumber.stream()
			.mapToInt(Integer::parseInt)
			.filter(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)
			.findFirst()
			.isPresent()) {
			throw new IllegalArgumentException("범위를 벗어난 번호가 포함되어 있습니다.");
		}
	}

	private void validateBonusNumber(int bonusNumber) {
		validateBonusDuplicate(bonusNumber);
		validateBonusRange(bonusNumber);
	}

	private void validateBonusDuplicate(int bonusNumber) {
		if (winningNumber.stream().anyMatch(number -> number == bonusNumber)) {
			throw new IllegalArgumentException("중복된 보너스 번호가 있습니다.");
		}
	}

	private void validateBonusRange(int bonusNumber) {
		if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException("보너스 번호가 범위를 벗어날 수 없습니다.");
		}
	}

	public List<Integer> getWinningNumber() {
		return winningNumber;
	}

	public int getBonusNumber() {
		return bonusNumber;
	}
}
