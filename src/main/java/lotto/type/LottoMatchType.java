package lotto.type;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoNumber;

public enum LottoMatchType {
    THREE_MATCH(3, 5000,
        "3개 일치 (5000원) - %d개"),
    FOUR_MATCH(4, 50000,
        "4개 일치 (50000원) - %d개"),
    FIVE_MATCH(5, 1500000,
        "5개 일치 (1500000원) - %d개"),
    FIVE_AND_BONUS_MATCH(6, 30000000,
        "5개 일치, 보너스 볼 일치(30000000원) - %d개"),
    SIX_MATCH(6, 2000000000,
        "6개 일치 (2000000000원) - %d개");

    private final int countMatchedNumbers;
    private final int prizeMoney;
    private final String matchCountMessage;

    LottoMatchType(int countMatchedNumbers, int prizeMoney,
        String matchCountMessage) {
        this.countMatchedNumbers = countMatchedNumbers;
        this.prizeMoney = prizeMoney;
        this.matchCountMessage = matchCountMessage;
    }

    public int getCountMatchedNumbers() {
        return countMatchedNumbers;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMatchCountMessage() {
        return matchCountMessage;
    }

    public static LottoMatchType getLottoMatchType(List<LottoNumber> matchedLottoNumbersToGetPrize) {
        if (matchedLottoNumbersToGetPrize.size() != SIX_MATCH.getCountMatchedNumbers()) {
            return getLottoMatchTypeNotSixNumbersMatched(matchedLottoNumbersToGetPrize);
        }
        return getLottoMatchTypeSixNumbersMatched(matchedLottoNumbersToGetPrize);
    }

    private static LottoMatchType getLottoMatchTypeNotSixNumbersMatched(List<LottoNumber> matchedLottoNumbers) {
        return Arrays.stream(LottoMatchType.values())
            .filter(lottoMatchType ->
                lottoMatchType.getCountMatchedNumbers() == matchedLottoNumbers.size())
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static LottoMatchType getLottoMatchTypeSixNumbersMatched(List<LottoNumber> matchedLottoNumbers) {
        if (matchedLottoNumbers.stream()
            .anyMatch(LottoNumber::isBonusNumber)) {
            return FIVE_AND_BONUS_MATCH;
        }
        return SIX_MATCH;
    }
}
