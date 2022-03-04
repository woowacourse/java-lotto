package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.domain.LottoRanking;
import lotto.domain.Result;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Money totalPrice() {
        return new Money(lottos.size() * Lotto.PRICE);
    }

    public Result getResult(WinningLotto winningLotto) {
        Map<LottoRanking, Integer> result = new EnumMap<LottoRanking, Integer>(LottoRanking.class);
        initResult(result);
        for (Lotto lotto : lottos) {
            LottoRanking lottoRanking = winningLotto.getLottoRanking(lotto);
            result.put(lottoRanking, result.get(lottoRanking) + 1);
        }
        return new Result(result);
    }

    private void initResult(Map<LottoRanking, Integer> result) {
        for (LottoRanking value : LottoRanking.values()) {
            result.put(value, 0);
        }
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
