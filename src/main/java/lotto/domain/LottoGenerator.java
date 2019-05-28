package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final List<LottoNumber> lottoNumberList;

    public LottoGenerator() {
        lottoNumberList = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            lottoNumberList.add(LottoNumber.of(i));
        }
    }

    public Lotto generate() {
        Collections.shuffle(lottoNumberList);
        List<LottoNumber> result = lottoNumberList.subList(0, 6);
        Collections.sort(result);
        return new Lotto(result);
    }
}
