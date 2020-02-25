package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoNumberGroup {
    private static Map<Integer, LottoNumber> lottoNumberGroup = new HashMap<>();

    private LottoNumberGroup(int number) {
        lottoNumberGroup.put(number, new LottoNumber(number));
    }

    public static LottoNumber getInstance(int number) {
        if (!lottoNumberGroup.containsKey(number)) {
            new LottoNumberGroup(number);
        }
        return lottoNumberGroup.get(number);
    }
}
