package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static Lotto create(LottoNoGenerator lottoNoGenerator) {
        List<Integer> lotto = new ArrayList<>();
        for (int position = 0; position < Lotto.LOTTO_SIZE; position++) {
            lotto.add(lottoNoGenerator.generate());
        }
        return Lotto.of(lotto);
    }
}
