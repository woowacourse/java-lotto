package domain.lotto;

public class WinNumbers {
    private static final String BONUS_DUPLICATED_ERROR_MESSAGE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";

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
            throw new IllegalArgumentException(BONUS_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public boolean contains(final LottoNumber number) {
        return lotto.contains(number);
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}