package lotto.domain;

public class WinningResult {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningResult(Lotto winningLotto, LottoNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.isDuplicateNumber(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호와 중복되지 않는 보너스 번호를 입력해 주세요.");
        }
    }
}
