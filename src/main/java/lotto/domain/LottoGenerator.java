package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private final List<LottoNumber> lottoNumberList;

    public LottoGenerator() {
        lottoNumberList = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
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
