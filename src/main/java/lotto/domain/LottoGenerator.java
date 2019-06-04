package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final List<Integer> lottoNumbers = new ArrayList<>();

    static {
        for (int i = Lotto.MIN_NUMBER; i <= Lotto.MAX_NUMBER; i++) {
            lottoNumbers.add(i);
        }
    }

    private LottoGenerator() {

    }

    private static List<Integer> getLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(0, Lotto.NUMBER_COUNT));
    }

    public static Lotto lotto() {
        return new Lotto(getLottoNumbers());
    }

}
