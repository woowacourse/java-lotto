package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        Objects.requireNonNull(manualLottos, "[ERROR] 수동 구매 로또는 null이 들어올 수 없습니다.");
        final List<Lotto> lottos = new ArrayList<>(manualLottos);
        checkNegativeAutomaticLottoCounts(automaticLottoCounts);

        return new Lottos(lottos);
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
