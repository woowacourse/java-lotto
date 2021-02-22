package lotto.domain.lotto;

public class WinningLotto {

    public static final String DUPLICATION_BONUS_BALL_ERROR = "중복된 보너스 볼 값입니다.";
    private final Lotto lotto;
    private final LottoNumber bonus;

    private WinningLotto(Lotto lotto, LottoNumber bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public static WinningLotto generatedBy(Lotto lotto, LottoNumber bonus) {
        validateBonus(lotto, bonus);
        return new WinningLotto(lotto, bonus);
    }

    private static void validateBonus(Lotto lotto, LottoNumber bonus) {
        if (lotto.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException(DUPLICATION_BONUS_BALL_ERROR);
        }
    }

    public Lotto getLotto() {
        return this.lotto;
    }

    public LottoNumber getBonus() {
        return this.bonus;
    }
}
