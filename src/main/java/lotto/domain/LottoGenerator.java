package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final List<LottoNumber> allLottoNumbers = new ArrayList<>();
    private static final int FIRST_LOTTO_INDEX = 0;
    private static final int LAST_LOTTO_INDEX = 5;
    private static final int ONE = 1;
    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_LOTTO_NUMBER = 1;

    private LottoGenerator() {
        for (int i = MIN_LOTTO_NUMBER; i < MAX_LOTTO_NUMBER; i++) {
            allLottoNumbers.add(new LottoNumber(i));
        }
    }

    public static LottoGenerator create() {
        return new LottoGenerator();
    }

    public static List<LottoNumber> generateLotto() {
        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers.subList(FIRST_LOTTO_INDEX, LAST_LOTTO_INDEX + ONE);
    }

}
