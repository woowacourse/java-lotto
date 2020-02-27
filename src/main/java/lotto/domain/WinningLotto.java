package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    public static final String NULL_OR_EMPTY_VALUE_ERROR = "널이나 빈 값이 들어올 수 없습니다.";
    public static final String LOTTOBALL_AMOUNT_ERROR = "로또 변호는 %s개여야 합니다.";
    public static final String DUPLICATE_BALL_ERROR = "중복된 번호가 있습니다.";
    public static final String BALLNUMBER_OUT_OF_RANGE = "범위를 벗어난 번호가 포함되어 있습니다.";
    public static final String DUPLICATE_BONUSBALL_NUMBER = "중복된 보너스 번호가 있습니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final List<String> winningNumberString, final LottoNumber bonusNumber) {
        validateWinningNumber(winningNumberString);
        this.winningLotto = new Lotto(winningNumberString.stream()
                .map(Integer::parseInt)
                .map(LottoMachine.getInstance()::pickBall)
                .collect(Collectors.toList()));

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
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE_ERROR);
        }
    }

	private void validateNumberAmount(List<String> winningNumber) {
		if (winningNumber.size() != Lotto.SIZE) {
			throw new IllegalArgumentException(String.format(LOTTOBALL_AMOUNT_ERROR, Lotto.SIZE));
		}
	}

	private void validateDuplicate(List<String> winningNumber) {
		if (winningNumber.size() != new HashSet<>(winningNumber).size()) {
			throw new IllegalArgumentException(DUPLICATE_BALL_ERROR);
		}
	}

    private void validateRange(List<String> winningNumber) {
        winningNumber.stream()
                .mapToInt(Integer::parseInt)
                .filter(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)
                .findFirst()
                .ifPresent(x -> {
                    throw new IllegalArgumentException(BALLNUMBER_OUT_OF_RANGE);
                });
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        validateBonusDuplicate(bonusNumber);
    }

    private void validateBonusDuplicate(LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUSBALL_NUMBER);
        }
    }


    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonusNumber == that.bonusNumber &&
                Objects.equals(winningLotto, that.winningLotto);
	}

	@Override
	public int hashCode() {
        return Objects.hash(winningLotto, bonusNumber);
    }
}
