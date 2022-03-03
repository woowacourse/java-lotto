package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoRanking;
import lotto.domain.Result;
import lotto.domain.factory.LottoFactory;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos(Count autoCount, List<Lotto> manualLottos) {
        lottos.addAll(manualLottos);
        int autoCountValue = autoCount.value();
        while (autoCountValue-- > 0) {
            lottos.add(LottoFactory.auto());
        }
    }

    public Money totalPrice() {
        return new Money(lottos.size() * Lotto.PRICE);
    }

    public Result getResult(WinningLotto winningLotto) {
        Result result = new Result();
        for (Lotto lotto : lottos) {
            LottoRanking ranking = winningLotto.getLottoRanking(lotto);
            result.add(ranking);
        }
        return result;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
