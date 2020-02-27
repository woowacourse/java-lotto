package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningLotto {
    public static final String NULL_OR_EMPTY_VALUE_ERROR = "널이나 빈 값이 들어올 수 없습니다.";
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
    }

    private void validateNullAndEmpty(List<String> winningNumber) {
        if (winningNumber == null || winningNumber.isEmpty()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE_ERROR);
        }
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
