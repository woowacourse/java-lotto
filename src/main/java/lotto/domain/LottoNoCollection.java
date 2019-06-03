package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNoCollection {
    private static final List<LottoNo> lottoNos = new ArrayList<>();

    static {
        for (int i = LottoNo.MIN_NUMBER; i <= LottoNo.MAX_NUMBER; i++) {
            lottoNos.add(LottoNo.from(i));
        }
    }

    public static List<LottoNo> createLottoNos() {
        return new ArrayList<>(lottoNos);
    }
}
