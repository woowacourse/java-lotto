package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    public static final String DELIMITER = ",";
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public List<Lotto> sell(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(lottoGenerator.generateAuto()));
        }
        return lottos;
    }
}
