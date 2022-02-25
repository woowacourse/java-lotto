package domain.lotto;

import java.util.List;

public class WinLotto extends Lotto {
    private final LottoNumber bonus;

    WinLotto(final List<LottoNumber> lottoNumbers, LottoNumber bonus) {
        super(lottoNumbers);
        this.bonus = bonus;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

}