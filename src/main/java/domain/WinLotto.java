package domain;

public class WinLotto {

    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "보너스 볼 번호가 지난 주 당첨 번호와 일치할 수 없습니다.";

    private final Lotto winLotto;
    private final LottoNumber bonusNumber;

    public WinLotto(final Lotto winLotto, final LottoNumber bonusNumber) {
        this.winLotto = winLotto;
        this.bonusNumber = bonusNumber;
        validate(winLotto, bonusNumber);
    }

    private void validate(final Lotto winLotto, final LottoNumber bonusNumber) {
        if (winLotto.isContainNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
        }
    }

    public Lotto getLotto() {
        return this.winLotto;
    }

    public LottoNumber getBonusNumber() {
        return this.bonusNumber;
    }
}
