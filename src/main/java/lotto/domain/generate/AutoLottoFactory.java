package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.utils.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.Lotto.LOTTO_NUMBER_SIZE;
import static lotto.domain.LottoNumber.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoNumber.MIN_LOTTO_NUMBER;

public class AutoLottoFactory {
    private static List<Integer> lottoNumbers = generateLottoNumber();

    static List<Lotto> generateAutoLottos(int tryNo) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < tryNo; i++) {
            lottos.add(generateAutoLotto());
        }
        return lottos;
    }

    private static Lotto generateAutoLotto() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.subList(0, LOTTO_NUMBER_SIZE));
    }


    private static List<Integer> generateLottoNumber() {
        IntStream stream = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        return stream.boxed().collect(Collectors.toList());
    }

}
