package lottogame.domain;

import java.util.Arrays;
import lottogame.domain.ticket.LottoTicketResult;

public enum Rank {
    RANK1(6, false, 2_000_000_000),
    RANK2(5, true, 30_000_000),
    RANK3(5, false, 1_500_000),
    RANK4(4, false, 50_000),
    RANK5(3, false, 5_000),
    FAIL(0, false, 0);

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int price;

    Rank(int matchCount, boolean isBonusMatch, int price) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.price = price;
    }

    public static Rank getRank(LottoTicketResult lottoTicketResult) {
        return Arrays.stream(values())
            .filter(rank -> rank.matchCount == lottoTicketResult.getMatchCount())
            .filter(rank -> rank.isBonusMatch == lottoTicketResult.isBonusMatched())
            .findAny()
            .orElse(FAIL);
    }

    public int getPrice() {
        return price;
    }
}
