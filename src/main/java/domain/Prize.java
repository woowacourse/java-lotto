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
        return calculate(matching, bonusMatching);
    }

    public static Prize calculate(final int matching, boolean bonusMatching) {
        if (needBonusChecking(matching)) {
            return considerBonus(matching, bonusMatching);
        }

        return notConsiderBonus(matching, bonusMatching);
    }

    private static boolean needBonusChecking(final int matching) {
        return matching == BONUS_CONSIDER_CRITERION;
    }

    private static Prize considerBonus(final int matching, final boolean bonusMatching) {
        return Arrays.stream(values())
                .filter(prize -> Objects.equals(matching, prize.matching))
                .filter(prize -> Objects.equals(bonusMatching, prize.bonusMatching))
                .findFirst()
                .orElse(NOTHING);
    }

    private static Prize notConsiderBonus(final int matching, final boolean bonusMatching) {
        return Arrays.stream(values())
                .filter(prize -> Objects.equals(matching, prize.matching))
                .findFirst()
                .orElse(NOTHING);
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
