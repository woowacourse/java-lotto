package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final List<LottoNumber> lottoNumberList;

    static {
        lottoNumberList = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
            lottoNumberList.add(LottoNumber.of(i));
        }
    }

    public static Lotto generate() {
        Collections.shuffle(lottoNumberList);
        List<LottoNumber> result = lottoNumberList.subList(0, 6);
        Collections.sort(result);
        return new Lotto(result);
    }
}
