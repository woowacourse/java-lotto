package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class LottoFactory {

    public Count calculateCount(final Money money) {
        return new Count(money.calculateCounts());
    }

    public LottoNumbers generateAutoLottoNumbers() {
        HashSet<LottoNumber> autoLottoNumbers = new HashSet<>();
        while (autoLottoNumbers.size() < 6) {
            autoLottoNumbers.add(generateAutoLottoNumber(ThreadLocalRandom.current().nextInt(45) + 1));
        }

        return new LottoNumbers(new ArrayList<>(autoLottoNumbers));
    }


    //ThreadLocalRandom.current().nextInt(45) + 1
    public LottoNumber generateAutoLottoNumber(final int number) {
        final int autoLottoNumber = number;
        return new LottoNumber(autoLottoNumber);

    }
}
