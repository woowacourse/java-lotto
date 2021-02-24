package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import lotto.domain.WinningLotto;
import lotto.model.LottoResult;

public class Lottos {

    public static final Lottos EMPTY_LOTTOS = new Lottos(Collections.emptyList());

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getNumOfLottos() {
        return this.getLottos().size();
    }

    public LottoResult match(WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            lottoResult.addRank(winningLotto.match(lotto));
        }
        return lottoResult;
    }
}
