package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public List<Lotto> sell(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i=0; i<count; i++) {
            lottos.add(new Lotto(lottoGenerator.generateLotto()));
        }
        return lottos;
    }
}
