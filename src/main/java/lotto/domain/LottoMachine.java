package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.vo.LottoNumber;

public class LottoMachine {

    private static final int START_INCLUSIVE = 0;
    private static final int END_EXCLUSIVE = 6;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private static final List<LottoNumber> LOTTO_NUMBERS = initNumbers();

    public static Lotto generate() {
        return new Lotto(shuffleNumbers().subList(START_INCLUSIVE, END_EXCLUSIVE));
    }

    private static List<LottoNumber> shuffleNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoMachine.LOTTO_NUMBERS);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    private static List<LottoNumber> initNumbers() {
        return IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());
    }
}
