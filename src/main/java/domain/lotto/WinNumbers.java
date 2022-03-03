package domain.lotto;

import exception.lotto.BonusNumDuplicatedException;

public class WinNumbers {
    private final Lotto lotto;
    private final LottoNumber bonus;

    private WinNumbers(final Lotto lotto, final LottoNumber bonus) {
        validate(lotto, bonus);
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public static WinNumbers of(final Lotto lotto, final LottoNumber bonus) {
        return new WinNumbers(lotto, bonus);
    }

    private static void validate(final Lotto lotto, final LottoNumber bonus) {
        if (lotto.contains(bonus)) {
            throw new BonusNumDuplicatedException();
        }
    }

    public boolean contains(final LottoNumber number) {
        return lotto.contains(number);
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}