package lotto.domain;

public enum LottoMatchType {
    THREE_MATCH(3, false, 5000,
        "3개 일치 (5000원) - %d개"),
    FOUR_MATCH(4, false, 50000,
        "4개 일치 (50000원) - %d개"),
    FIVE_MATCH(5, false, 1500000,
        "5개 일치 (1500000원) - %d개"),
    FIVE_AND_BONUS_MATCH(5, true, 30000000,
        "5개 일치, 보너스 볼 일치(30000000원) - %d개"),
    SIX_MATCH(6, false, 2000000000,
        "6개 일치 (2000000000원) - %d개");

    private final int countMatchedNumbers;
    private final boolean is2ndPrize;
    private final int prizeMoney;
    private final String matchCountMessage;

    LottoMatchType(int countMatchedNumbers, boolean is2ndPrize, int prizeMoney,
        String matchCountMessage) {
        this.countMatchedNumbers = countMatchedNumbers;
        this.is2ndPrize = is2ndPrize;
        this.prizeMoney = prizeMoney;
        this.matchCountMessage = matchCountMessage;
    }

    public int getCountMatchedNumbers() {
        return countMatchedNumbers;
    }

    public boolean is2ndPrize() {
        return is2ndPrize;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMatchCountMessage() {
        return matchCountMessage;
    }
}
