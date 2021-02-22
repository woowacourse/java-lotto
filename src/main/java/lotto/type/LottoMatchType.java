package lotto.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private final int countMatchedNumbers;
    private final int prizeMoney;
    private final String matchCountMessage;

    LottoMatchType(int countMatchedNumbers, int prizeMoney,
                   String matchCountMessage) {
        this.countMatchedNumbers = countMatchedNumbers;
        this.prizeMoney = prizeMoney;
        this.matchCountMessage = matchCountMessage;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMatchCountMessage() {
        return matchCountMessage;
    }

    public static List<LottoMatchType> getLottoMatchType(int countMatchedNumbers) {
        return Arrays.stream(LottoMatchType.values())
                .filter(lottoMatchType -> lottoMatchType.countMatchedNumbers == countMatchedNumbers)
                .collect(Collectors.toList());
    }
}
