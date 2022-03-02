package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoRanking;
import lotto.domain.Money;
import lotto.domain.Result;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
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
        return lottos;
    }
}
