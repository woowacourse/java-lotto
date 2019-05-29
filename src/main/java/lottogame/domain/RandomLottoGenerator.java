package lottogame.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class RandomLottoGenerator {
    private static final int LOTTO_SIZE = 6;

    static Lotto create() {
        Set<LottoNumber> lotto = new HashSet<>();
        while (lotto.size() < LOTTO_SIZE) {
            addRandomLottoNumber(lotto);
        }
        return new Lotto(new ArrayList<>(lotto));
    }

    private static void addRandomLottoNumber(Set<LottoNumber> lotto) {
        LottoNumber randomLottoNumber = LottoNumber.getRandomLottoNumber();
        lotto.add(randomLottoNumber);
    }
}
