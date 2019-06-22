package model;

import java.util.Collections;
import java.util.List;

public class WinningNumbersVO {
    private final List<LottoNumber> winningNumbers;
    private final int round;

    public WinningNumbersVO(List<LottoNumber> winningNumbers, int round) {
        this.winningNumbers = Collections.unmodifiableList(winningNumbers);
        this.round = round;
    }

    public List<LottoNumber> mainNumbers() {
        return this.winningNumbers.subList(0, Lotto.NUMBER_OF_PICKS);
    }

    public LottoNumber bonusNumber() {
        return this.winningNumbers.get(Lotto.NUMBER_OF_PICKS);
    }

    public int round() {
        return this.round;
    }
}
