package lotto.model;

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
        final Set<LottoNumber> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < Lotto.NUMBER_COUNT) {
            uniqueNumbers.add(
                    getRandomLottoNumberInRange(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)
            );
        }
        return Set.copyOf(uniqueNumbers);
    }

    private static LottoNumber getRandomLottoNumberInRange(int start, int end) {
        final Random random = new Random();
        return new LottoNumber(random.nextInt(end - start) + start);
    }
}
