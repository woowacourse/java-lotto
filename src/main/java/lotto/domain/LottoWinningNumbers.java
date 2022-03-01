package lotto.domain;

public class LottoWinningNumbers {

    private static final String ERROR_DUPLICATE_BONUS_NUMBER = "[ERROR] 보너스번호는 로또번호와 중복되지 않아야 합니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public LottoWinningNumbers(final Lotto winningLotto, final LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        checkDuplicateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateBonusNumber(final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public int matchCount(Lotto lotto) {
        return (int) winningLotto.getNumbers()
                .stream()
                .filter(number -> lotto.contains(number))
                .count();
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}