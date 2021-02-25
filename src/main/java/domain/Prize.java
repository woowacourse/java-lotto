package domain;

import domain.ticket.LottoTicket;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return this.matching == matching && this.bonusMatching == bonusMatching;
        }
    },
    THIRD(5, false, 1_500_000L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return this.matching == matching && this.bonusMatching == bonusMatching;
        }
    },
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5000L),
    NOTHING(0, false, 0L);

    protected final int matching;
    protected final boolean bonusMatching;
    protected final long money;

    Prize(final int matching, final boolean bonusMatching, final long money) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
    }

    boolean isMatched(final int matching, final boolean bonusMatching) {
        return this.matching == matching;
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
