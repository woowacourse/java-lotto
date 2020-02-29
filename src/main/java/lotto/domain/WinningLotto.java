package lotto.domain;

import java.util.Objects;

public class WinningLotto {
    public static final String NULL_OR_EMPTY_VALUE_ERROR = "널이나 빈 값이 들어올 수 없습니다.";
    public static final String DUPLICATE_BONUSBALL_NUMBER = "중복된 보너스 번호가 있습니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusNumber) {
        validateNullAndEmpty(winningLotto);
        this.winningLotto = winningLotto;

        validateBonusDuplicate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateNullAndEmpty(Lotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_VALUE_ERROR);
        }
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
