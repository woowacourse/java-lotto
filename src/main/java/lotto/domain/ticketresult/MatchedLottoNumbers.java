package lotto.domain.ticketresult;

import static lotto.type.LottoMatchType.FIVE_AND_BONUS_MATCH;
import static lotto.type.LottoMatchType.FIVE_MATCH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.type.LottoMatchType;

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

    public LottoMatchType getMatchType() {
        if (numbersNotIncludingBonusNumber.size()
            != FIVE_MATCH.getCountOfMatchedNumbersNotIncludingBonusNumber()) {
            return getLottoMatchTypeNotFiveNumbersMatched();
        }
        return getLottoMatchTypeFiveNumbersMatched();
    }

    private LottoMatchType getLottoMatchTypeNotFiveNumbersMatched() {
        return Arrays.stream(LottoMatchType.values())
            .filter(lottoMatchType ->
                lottoMatchType.getCountOfMatchedNumbersNotIncludingBonusNumber()
                    == numbersNotIncludingBonusNumber.size())
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
        int size = numbersNotIncludingBonusNumber.size();
        if (bonusNumber != null) {
            size++;
        }
        return size;
    }
}
