package domain;

import domain.ticket.LottoTicket;

import java.util.Arrays;

public enum Prize {
    FIRST(6, false, 2_000_000_000L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return this.matching == matching;
        }
    },
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
    FOURTH(4, false, 50_000L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return this.matching == matching;
        }
    },
    FIFTH(3, false, 5000L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return this.matching == matching;
        }
    },
    NOTHING(0, false, 0L) {
        @Override
        boolean isMatched(final int matching, final boolean bonusMatching) {
            return false;
        }
    };

    protected final int matching;
    protected final boolean bonusMatching;
    protected final long money;

    Prize(final int matching, final boolean bonusMatching, final long money) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
    }

    abstract boolean isMatched(final int matching, final boolean bonusMatching);

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
