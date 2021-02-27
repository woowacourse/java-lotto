package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import lotto.domain.WinningLotto;
import lotto.domain.lottoresult.LottoResult;

public class Lottos {

    public static final Lottos EMPTY_LOTTOS = new Lottos(Collections.emptyList());

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos from(List<Lotto> lottos) {
        if (lottos.size() == 0) {
            return EMPTY_LOTTOS;
        }
        return new Lottos(lottos);
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
