package domain;

import domain.ticket.LottoTicket;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Prize {
    FIRST(6, false, 2_000_000_000L, (matching, bonusMatching) -> matching == 6),
    SECOND(5, true, 30_000_000L, (matching, bonusMatching) -> matching == 5 && bonusMatching),
    THIRD(5, false, 1_500_000L, (matching, bonusMatching) -> matching == 5 && !bonusMatching),
    FOURTH(4, false, 50_000L, (matching, bonusMatching) -> matching == 4),
    FIFTH(3, false, 5000L, (matching, bonusMatching) -> matching == 3),
    NOTHING(0, false, 0L, (matching, bonusMatching) -> false);

    private final int matching;
    private final boolean bonusMatching;
    private final long money;
    private final BiPredicate<Integer, Boolean> matchExpression;

    Prize(final int matching, final boolean bonusMatching, final long money, final BiPredicate<Integer, Boolean> matchExpression) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
        this.matchExpression = matchExpression;
    }

    public static Prize of(final WinningNumbers winningNumbers, final LottoTicket lottoTicket) {
        final int matching = winningNumbers.countMatching(lottoTicket);
        boolean bonusMatching = winningNumbers.hasBonus(lottoTicket);

        return choice(matching, bonusMatching);
    }

    public static Prize choice(final int matching, final boolean bonusMatching) {
        return Arrays.stream(values())
                .filter(prize -> prize.isMatched(matching, bonusMatching))
                .findFirst()
                .orElse(NOTHING);
    }

    boolean isMatched(final int matching, final boolean bonusMatching) {
        return matchExpression.test(matching, bonusMatching);
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
