package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public LottoGroup sellAuto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoGenerator.generateAuto());
        }
        return new LottoGroup(lottos);
    }

    public Lotto sellManual(String input) {
        return lottoGenerator.generateManual(input);
    }
}
