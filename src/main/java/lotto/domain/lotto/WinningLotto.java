package lotto.domain.lotto;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonus;

    private WinningLotto(Lotto lotto, LottoNumber bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public static WinningLotto generatedBy(Lotto lotto, LottoNumber bonus) {
        validateWinningLotto(lotto, bonus);
        return new WinningLotto(lotto, bonus);
    }

    private static void validateWinningLotto(Lotto lotto, LottoNumber bonus) {
        validateBonus(lotto, bonus);
    }

    private static void validateBonus(Lotto lotto, LottoNumber bonus) {
        if (lotto.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException("중복된 보너스 볼 값입니다.");
        }
    }

    public Lotto getLotto() {
        return this.lotto;
    }

    public LottoNumber getBonus() {
        return this.bonus;
    }
}
