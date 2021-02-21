package lotto.type;

import java.util.Arrays;
import lotto.domain.ticketresult.MatchedLottoNumbers;

public enum LottoMatchType {
    THREE_MATCH(3, 5_000,
        "3개 일치 (5000원) - %d개"),
    FOUR_MATCH(4, 50_000,
        "4개 일치 (50000원) - %d개"),
    FIVE_MATCH(5, 1_500_000,
        "5개 일치 (1500000원) - %d개"),
    FIVE_AND_BONUS_MATCH(5, 30_000_000,
        "5개 일치, 보너스 볼 일치(30000000원) - %d개"),
    SIX_MATCH(6, 2_000_000_000,
        "6개 일치 (2000000000원) - %d개");

    private final int countOfMatchedNumbersNotIncludingBonusNumber;
    private final int prizeMoney;
    private final String matchCountMessage;

    LottoMatchType(int countOfMatchedNumbersNotIncludingBonusNumber, int prizeMoney,
        String matchCountMessage) {
        this.countOfMatchedNumbersNotIncludingBonusNumber = countOfMatchedNumbersNotIncludingBonusNumber;
        this.prizeMoney = prizeMoney;
        this.matchCountMessage = matchCountMessage;
    }

    public int getCountOfMatchedNumbersNotIncludingBonusNumber() {
        return countOfMatchedNumbersNotIncludingBonusNumber;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMatchCountMessage() {
        return matchCountMessage;
    }

    public static LottoMatchType getLottoMatchType(
        MatchedLottoNumbers matchedLottoNumbersToGetPrize) {
        if (matchedLottoNumbersToGetPrize.getSizeOfNumbersNotIncludingBonusNumber()
            != FIVE_MATCH.getCountOfMatchedNumbersNotIncludingBonusNumber()) {
            return getLottoMatchTypeNotFiveNumbersMatched(matchedLottoNumbersToGetPrize);
        }
        return getLottoMatchTypeFiveNumbersMatched(matchedLottoNumbersToGetPrize);
    }

    private static LottoMatchType getLottoMatchTypeNotFiveNumbersMatched(
        MatchedLottoNumbers matchedLottoNumbers) {
        return Arrays.stream(LottoMatchType.values())
            .filter(lottoMatchType ->
                lottoMatchType.getCountOfMatchedNumbersNotIncludingBonusNumber()
                    == matchedLottoNumbers.getSizeOfNumbersNotIncludingBonusNumber())
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static LottoMatchType getLottoMatchTypeFiveNumbersMatched(
        MatchedLottoNumbers matchedLottoNumbers) {
        if (matchedLottoNumbers.isContainsBonusNumber()) {
            return FIVE_AND_BONUS_MATCH;
        }
        return FIVE_MATCH;
    }
}
