package lotto.domain.ticketresult;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.type.LottoMatchType;

public class MatchedLottoNumbers {
    private final List<LottoNumber> numbersExceptBonusNumber;
    private final LottoNumber bonusNumber;

    public MatchedLottoNumbers(List<LottoNumber> numbersExceptBonusNumber,
        LottoNumber bonusNumber) {
        this.numbersExceptBonusNumber = numbersExceptBonusNumber;
        this.bonusNumber = bonusNumber;
    }

    public int getSizeExceptBonusNumber() {
        return numbersExceptBonusNumber.size();
    }

    public LottoMatchType getMatchType() {
        if (numbersExceptBonusNumber.size()
            != FIVE_MATCH.getCountExceptBonusNumber()) {
            return getLottoMatchTypeNotFiveNumbersMatched();
        }
        return getLottoMatchTypeFiveNumbersMatched();
    }

    private LottoMatchType getLottoMatchTypeNotFiveNumbersMatched() {
        return Arrays.stream(LottoMatchType.values())
            .filter(lottoMatchType ->
                lottoMatchType.getCountExceptBonusNumber() == numbersExceptBonusNumber.size())
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    private LottoMatchType getLottoMatchTypeFiveNumbersMatched() {
        if (isContainsBonusNumber()) {
            return FIVE_AND_BONUS_MATCH;
        }
        return FIVE_MATCH;
    }

    public boolean isContainsBonusNumber() {
        return bonusNumber != null;
    }

    public int size() {
        int size = numbersExceptBonusNumber.size();
        if (bonusNumber != null) {
            size++;
        }
        return size;
    }
}
