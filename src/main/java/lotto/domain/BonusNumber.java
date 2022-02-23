package lotto.domain;

import java.util.List;
import lotto.receiver.BonusNumberReceiver;

public class BonusNumber {
    private final LottoNumber bonusNumber;

    public BonusNumber(String input, List<LottoNumber> winningNumbers) {
        this.bonusNumber = BonusNumberReceiver.receive(input, winningNumbers);
    }
}
