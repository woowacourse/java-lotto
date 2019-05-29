package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoAutoGenerator {
    private static final int FIRST_LOTTO_INDEX = 0;
    private static final int LAST_LOTTO_INDEX = 5;
    private static final int ONE = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final List<LottoNumber> allLottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .forEach(allLottoNumbers::add);
    }

    private LottoAutoGenerator() {
    }

    public static List<LottoNumber> generateAutoLotto() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> generatedLotto = allLottoNumbers.subList(FIRST_LOTTO_INDEX, LAST_LOTTO_INDEX + ONE);
        Collections.sort(generatedLotto);
        return generatedLotto;
    }

}
