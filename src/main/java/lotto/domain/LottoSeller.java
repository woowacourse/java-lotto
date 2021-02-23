package lotto.domain;

import lotto.exception.LottoPriceException;
import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoSeller {

    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_COUNT = 1;

    public LottoGroup sellLotto(final Money money) {
        int count = money.divide(LOTTO_PRICE);
        if (count < MIN_COUNT) {
            throw new LottoPriceException("가격이 부족합니다.");
        }

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoGenerator.generate());
        }
        return new LottoGroup(lottos, count);
    }

    public LottoGroup sellManualLotto(final Money money, final int manualCount, List<Lotto> manualLottos) {
        int count = money.divide(LOTTO_PRICE);
        if (count < MIN_COUNT || count < manualCount) {
            throw new LottoPriceException("가격이 부족합니다.");
        }

        List<Lotto> lottos = new ArrayList<>(manualLottos);
        for (int i = 0; i < count - manualCount; i++) {
            lottos.add(LottoGenerator.generate());
        }
        return new LottoGroup(lottos, count - manualCount, manualCount);
    }

    public static int lottoPrice() {
        return LOTTO_PRICE;
    }
}
