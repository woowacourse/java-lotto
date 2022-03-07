package lotto.domain.lotto;

public class LottoWinningNumbers {

    private static final String ERROR_CREATE_LOTTO = "[ERROR] 잘못된 숫자 입력입니다.";
    private static final String ERROR_DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 숫자 입력입니다.";

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public LottoWinningNumbers(final Lotto winningLotto, final LottoNumber bonusNumber) {
        checkNumbers(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkNumbers(final Lotto winningLotto, final LottoNumber bonusNumber) {
        checkNullEmpty(winningLotto, bonusNumber);
        checkDuplicateBonusNumber(winningLotto, bonusNumber);
    }

    private void checkNullEmpty(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto == null || bonusNumber == null) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    private void checkDuplicateBonusNumber(final Lotto winningLotto, final LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber.getLottoNumber())) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber.getLottoNumber();
    }
}
