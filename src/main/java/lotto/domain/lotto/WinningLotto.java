package lotto.domain.lotto;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto lotto, LottoNumber bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public Lotto getLotto(){
        return this.lotto;
    }

    public LottoNumber getBonus(){
        return this.bonus;
    }
}
