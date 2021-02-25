package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public LottoGroup sell(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(lottoGenerator.generateAuto()));
        }
        return new LottoGroup(lottos);
    }
}
