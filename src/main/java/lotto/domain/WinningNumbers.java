package lotto.domain;

import java.util.List;
import lotto.receiver.BonusNumberReceiver;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(String winningNumbersInput, String bonusNumberInput) {
        this.winningLotto = Lotto.generateLottoByManual(winningNumbersInput);
        this.bonusNumber = BonusNumberReceiver.receive(bonusNumberInput, winningLotto);
    }

    public int getWinningLottoMatchCount(List<LottoNumber> lotto) {
        return (int) winningLotto.getLotto().stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isBonusNumberContainedAt(List<LottoNumber> lotto) {
        return lotto.contains(bonusNumber);
    }
}
