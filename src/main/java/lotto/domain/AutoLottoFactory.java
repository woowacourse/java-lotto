package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.Number.MAX_LOTTO_NUMBER;
import static lotto.domain.Number.MIN_LOTTO_NUMBER;

public class AutoLottoFactory {
    public static final int LOTTO_NUMBER_SIZE = 6;
    private static List<Integer> lottoNumbers = generateLottoNumber();

    static Lotto generateLotto() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.subList(0,LOTTO_NUMBER_SIZE));
    }

    static List<Integer> generateLottoNumber() {
        IntStream stream = IntStream.range(MIN_LOTTO_NUMBER,MAX_LOTTO_NUMBER);
        return stream.boxed().collect(Collectors.toList());
    }

}
