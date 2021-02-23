package util;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int EXTRA_BOUND = 1;
    private static final int LOTTO_BALLS_IN_LOTTO = 6;

    private LottoGenerator() {
    }

    public static LottoBundle createRandomLottoBundle(final int number) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            lottos.add(createRandomLotto());
        }
        return new LottoBundle(lottos);
    }

    private static Lotto createRandomLotto() {
        final List<LottoBall> lottoBalls = new ArrayList<>();
        IntStream intStream = new Random().ints(MIN_NUMBER, MAX_NUMBER + EXTRA_BOUND);
        intStream.distinct()
                .limit(LOTTO_BALLS_IN_LOTTO)
                .sorted()
                .forEach(number -> lottoBalls.add(LottoBall.valueOf(number)));
        return new Lotto(lottoBalls);
    }
}

