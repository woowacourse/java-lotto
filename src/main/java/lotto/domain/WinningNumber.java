package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
	private final LottoNumbers winningNumber;
	private final LottoNumber bonusNumber;

	public WinningNumber(final List<String> winningNumber, final int bonusNumber) {
		validateWinningNumber(winningNumber);
		this.winningNumber = new LottoNumbers(winningNumber.stream()
			.map(number -> new LottoNumber(Integer.parseInt(number)))
			.collect(Collectors.toList()));

		validateBonusNumber(bonusNumber);
		this.bonusNumber = new LottoNumber(bonusNumber);
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
		if (winningNumber.size() != Lotto.SIZE) {
			throw new IllegalArgumentException(String.format("로또 번호는 %s개여야 합니다.", Lotto.SIZE));
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
			.filter(number -> number < LottoNumber.MINIMUM || number > LottoNumber.MAXIMUM)
			.findFirst()
			.isPresent()) {
			throw new IllegalArgumentException("범위를 벗어난 번호가 포함되어 있습니다.");
		}
	}

	private void validateBonusNumber(int bonusNumber) {
		validateBonusDuplicate(bonusNumber);
	}

	private void validateBonusDuplicate(int bonusNumber) {
		if (winningNumber.contains(new LottoNumber(bonusNumber))) {
			throw new IllegalArgumentException("중복된 보너스 번호가 있습니다.");
		}
	}

	public LottoNumbers getWinningNumber() {
		return winningNumber;
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}
}
