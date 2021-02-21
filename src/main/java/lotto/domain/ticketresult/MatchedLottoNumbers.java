package lotto.domain.ticketresult;

import java.util.List;
import lotto.domain.LottoNumber;

public class MatchedLottoNumbers {
    private final List<LottoNumber> numbersNotIncludingBonusNumber;
    private final LottoNumber bonusNumber;

    public MatchedLottoNumbers(
        List<LottoNumber> matchedLottoNumbersNotIncludingBonusNumber,
        LottoNumber matchedBonusNumber) {

        this.numbersNotIncludingBonusNumber = matchedLottoNumbersNotIncludingBonusNumber;
        this.bonusNumber = matchedBonusNumber;
    }

    public int getSizeOfNumbersNotIncludingBonusNumber() {
        return numbersNotIncludingBonusNumber.size();
    }

    public boolean isContainsBonusNumber() {
        return bonusNumber != null;
    }
}
