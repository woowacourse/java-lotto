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

    private static final List<LottoNumber> LOTTO_NUMBERS = LottoNumber.values();

    private LottoMachine() {}

    public static List<Lotto> generateLottos(int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> new Lotto(shuffleNumbers().subList(START_INCLUSIVE, END_EXCLUSIVE)))
            .collect(Collectors.toList());
    }

    private static List<LottoNumber> shuffleNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoMachine.LOTTO_NUMBERS);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
