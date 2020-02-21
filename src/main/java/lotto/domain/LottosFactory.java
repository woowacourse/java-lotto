package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    private final LottoFactory lottoFactory;

    public LottosFactory(LottoFactory lottoFactory) {
        this.lottoFactory = lottoFactory;
    }

    public Lottos createLottosByCount(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = count.getLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoFactory.create());
        }
        return new Lottos(lottos);
    }
}
