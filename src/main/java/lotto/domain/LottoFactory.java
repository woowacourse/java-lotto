package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final int START_LOTTO_NO = 1;
    private static final int END_LOTTO_NO = 45;
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;

    private static List<LottoNo> lottoNumberBox = new ArrayList<>();

    static {
        for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
            lottoNumberBox.add(new LottoNo(count));
        }
    }

    public static List<Lotto> createLotteries(Money money) {
        List<Lotto> lotteris = new ArrayList<>();
        int createCount = money.divideThousand();
        for (int i = 0; i < createCount; i++) {
            lotteris.add(createLotto());
        }
        return lotteris;
    }

    private static Lotto createLotto() {
        List<LottoNo> lotto = pickSixRandomNo();
        Collections.sort(lotto);
        return new Lotto(lotto);
    }

    private static List<LottoNo> pickSixRandomNo() {
        List<LottoNo> lotto = new ArrayList<>();
        lotto.addAll(lottoNumberBox);
        Collections.shuffle(lotto);
        lotto = lotto.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
        return lotto;
    }
}
