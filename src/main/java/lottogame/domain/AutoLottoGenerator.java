package lottogame.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AutoLottoGenerator {
    static Lotto create() {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < Lotto.LOTTO_SIZE) {
            addRandomLottoNumber(lotto);
        }
        return new Lotto(new ArrayList<>(lotto));
    }

    private static void addRandomLottoNumber(Set<LottoNumber> lotto) {
        LottoNumber randomLottoNumber = LottoNumber.getRandomLottoNumber();
        lotto.add(randomLottoNumber);
    }
}
