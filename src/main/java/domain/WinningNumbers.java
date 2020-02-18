package domain;

import java.util.Set;

public class WinningNumbers extends AbstractNumbersContainer {

    private final Set<LottoNumber> lastWeekNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Set<LottoNumber> lastWeekNumbers, LottoNumber bonusNumber) {
        validateSize(lastWeekNumbers);
        this.lastWeekNumbers = lastWeekNumbers;
        this.bonusNumber = bonusNumber;
    }
}