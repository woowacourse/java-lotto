package lotto.domain.lotto;

import java.util.List;

public class Lottos {

    private final List<Lotto> values;

    public Lottos(List<Lotto> values) {
        this.values = values;
    }

    public Integer size() {
        return values.size();
    }

    public List<Lotto> getLottos(){
        return this.values;
    }

    public void check(LottoResult lottoResult, WinningLotto winningLotto) {
        for (Lotto lotto : values) {
            lottoResult.checkWinningLotto(lotto, winningLotto);
        }
    }
}
