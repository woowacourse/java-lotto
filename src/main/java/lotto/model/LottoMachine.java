package lotto.model;

import static lotto.constant.LottoNumberConstants.LOTTO_NUMBER_COUNT;
import static lotto.constant.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.constant.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public final class LottoMachine {

    private LottoMachine() {
    }

    public static Lotto issue() {
        return new Lotto(generateNumbers());
    }

    private static Set<LottoNumber> generateNumbers() {
        Set<LottoNumber> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < LOTTO_NUMBER_COUNT.value()) {
            uniqueNumbers.add(getRandomLottoNumberInRange(LOTTO_NUMBER_MIN.value(), LOTTO_NUMBER_MAX.value()));
        }
        return Set.copyOf(uniqueNumbers);
    }

    private static LottoNumber getRandomLottoNumberInRange(int start, int end) {
        Random random = new Random();
        return new LottoNumber(random.nextInt(end - start) + start);
    }
}
