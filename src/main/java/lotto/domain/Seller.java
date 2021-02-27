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

    public LottoGroup sellManual(List<String> input) {
        List<Lotto> lottos = new ArrayList<>();
        for (String lottoNumbers : input) {
            lottos.add(lottoGenerator.generateManual(lottoNumbers));
        }
        return new LottoGroup(lottos);
    }
}
