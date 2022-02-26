package lotto.domain.vo;

import java.util.Objects;

public class WinningNumbers {

    private static final String ERROR_DUPLICATION_BONUS_MESSAGE = "보너스 숫자는 로또 숫자와 중복되면 안됩니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto winningLotto, LottoNumber bonusNumber) {
        validateBonusNumbersDuplication(winningLotto, bonusNumber);

        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumbersDuplication(
            Lotto lottoNumbers,
            LottoNumber bonusNumber
    ) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_MESSAGE);
        }
    }

    public boolean containsLottoNumber(LottoNumber number) {
        return winningLotto.contains(number);
    }

    public boolean equalsBonusNumber(LottoNumber number) {
        return bonusNumber.equals(number);
    }

    @Override
    public String toString() {
        return "WinningNumbers{" +
                "lottoNumbers=" + winningLotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(winningLotto, that.winningLotto) && Objects
                .equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonusNumber);
    }
}
