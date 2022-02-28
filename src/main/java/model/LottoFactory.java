package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final LottoFactory LOTTO_FACTORY = new LottoFactory();

    private static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> lottoNumberPool;

    private LottoFactory() {
        lottoNumberPool = createLottoNumbers();
    }

    public static LottoFactory getInstance() {
        return LOTTO_FACTORY;
    }

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
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
        Collections.shuffle(lottoNumberPool);
        return Lotto.from(new ArrayList<>(lottoNumberPool.subList(0, 6)));
    }
}
