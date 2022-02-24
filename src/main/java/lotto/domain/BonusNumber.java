package lotto.domain;

import lotto.receiver.BonusNumberReceiver;

public class BonusNumber {

    private final LottoNumber bonusNumber;

    public BonusNumber(String input, WinningLotto winningLotto) {
        this.bonusNumber = BonusNumberReceiver.receive(input, winningLotto);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
