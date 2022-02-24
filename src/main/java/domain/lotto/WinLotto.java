package domain.lotto;

import java.util.List;

public class WinLotto extends Lotto {
    private final LottoBall bonus;

    WinLotto(final List<LottoBall> lottoBalls, LottoBall bonus) {
        super(lottoBalls);
        this.bonus = bonus;
    }

    public LottoBall getBonus() {
        return bonus;
    }
}