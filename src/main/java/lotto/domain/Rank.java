package lotto.domain;

import static lotto.domain.LottoResult.*;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 150_000),
    BONUS(5, 30_000_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    private int matchCount;
    private int prizeAmount;

    Rank(int matchCount, int prizeAmount) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
    }

    public static Rank find(LottoTicket ticket, LottoNumber bonus, int matchCount) {
        if (isBonus(bonus, ticket, matchCount)) {
            return BONUS;
        }
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.getMatchCount() == matchCount)
            .findFirst()
            .orElse(NONE);
    }

    private static boolean isBonus(LottoNumber bonus, LottoTicket ticket, int matchCount) {
        return matchCount == BONUS_COUNT && ticket.contains(bonus);
    }

    public int getPrize() {
        return prizeAmount;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
