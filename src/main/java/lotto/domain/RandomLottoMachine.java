package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine {

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .forEach(lottoNumbers::add);
    }

    private RandomLottoMachine() {
    }

    public static List<LottoNumber> createRandomLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        return IntStream.rangeClosed(0, 5)
                .boxed()
                .map(lottoNumbers::get)
                .sorted()
                .collect(Collectors.toList());
    }
}
