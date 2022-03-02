package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    private LottoGenerator() {}

    public static Lottos pickAutoLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>(lottoCount);
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(Lotto.selectRandomBalls()));
        }
        return new Lottos(lottos);
    }
}
