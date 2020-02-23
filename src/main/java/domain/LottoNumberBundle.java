package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberBundle {
    private static List<LottoNumber> lottoNumberBundle;

    private LottoNumberBundle() {}

    static {
        lottoNumberBundle = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumberBundle.add(new LottoNumber(i));
        }
    }

    public static boolean contains(LottoNumber lottoNumber) {
        return lottoNumberBundle.contains(lottoNumber);
    }
}
