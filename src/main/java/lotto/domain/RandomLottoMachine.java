package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine {

    private static final List<LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    private RandomLottoMachine() {
    }

    public static Lottos buyLotto(final List<Lotto> manualLottos, final int automaticLottoCounts) {
        checkNegativeAutomaticLottoCounts(automaticLottoCounts);
        return null;
    }

    private static void checkNegativeAutomaticLottoCounts(int automaticLottoCounts) {
        if (automaticLottoCounts < 0) {
            throw new IllegalArgumentException("[ERROR] 랜덤 로또 구매 갯수는 음수가 들어올 수 없습니다.");
        }
    }

    public static Lotto createRandomLotto() {
        return new Lotto(createRandomLottoNumbers());
    }

    private static List<LottoNumber> createRandomLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        return IntStream.range(0, Lotto.LOTTO_NUMBER_SIZE_STANDARD)
                .boxed()
                .map(LOTTO_NUMBERS::get)
                .sorted()
                .collect(Collectors.toList());
    }
}
