package domain;

import domain.ticket.LottoTicket;

import java.util.Arrays;
import java.util.Objects;

public enum Prize {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5000L),
    NOTHING(0, false, 0L);

    private static final int BONUS_CONSIDER_CRITERION = 5;

    private final int matching;
    private final boolean bonusMatching;
    private final long money;

    Prize(final int matching, final boolean bonusMatching, final long money) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
    }

    public static Prize of(final WinningNumbers winningNumbers, final LottoTicket lottoTicket) {
        final int matching = winningNumbers.countMatching(lottoTicket);
        boolean bonusMatching = winningNumbers.hasBonus(lottoTicket);
        return transform(matching, bonusMatching);
    }

    private static Prize transform(final int matching, final boolean bonusMatching) {
        return Arrays.stream(values())
                .map(prize -> prize.transformIfSecondOrThird(matching, bonusMatching))
                .findFirst()
                .orElse(NOTHING);
    }

    private Prize transformIfSecondOrThird(final int matching, final boolean bonusMatching) {
        if (matching == BONUS_CONSIDER_CRITERION) {
            return getSecondOrThird(bonusMatching);
        }
        return this;
    }

    private Prize getSecondOrThird(final boolean bonusMatching) {
        if (bonusMatching) {
            return SECOND;
        }
        return THIRD;
    }

    public long getMoney() {
        return money;
    }

    public int getMatching() {
        return matching;
    }

    public boolean isBonusMatching() {
        return bonusMatching;
    }
}
