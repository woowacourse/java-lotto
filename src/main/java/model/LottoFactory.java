package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final LottoFactory LOTTO_FACTORY = new LottoFactory();

    private static final int LOTTO_PRICE = 1000;

    private LottoFactory() {

    }

    public static LottoFactory getInstance() {
        return LOTTO_FACTORY;
    }

    public List<Lotto> generateLotteries(LottoPurchasingMoney lottoPurchasingMoney) {
        return IntStream.range(0, getAvailableLottoCount(lottoPurchasingMoney))
                .mapToObj(i -> generateAuto())
                .collect(Collectors.toUnmodifiableList());
    }

    private int getAvailableLottoCount(LottoPurchasingMoney lottoPurchasingMoney) {
        return lottoPurchasingMoney.getAmount() / LOTTO_PRICE;
    }

    private Lotto generateAuto() {
        List<LottoNumber> lottoNumberPool = new ArrayList<>(LottoNumber.LOTTO_NUMBER_POOL.values());
        Collections.shuffle(lottoNumberPool);
        return Lotto.from(new ArrayList<>(lottoNumberPool.subList(0, 6)));
    }
}
