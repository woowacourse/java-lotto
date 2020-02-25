package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static Lottos createLottosByCount(LottoCount count) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = count.getLottoCount();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoFactory.createRandom());
        }
        return new Lottos(lottos);
    }
}
