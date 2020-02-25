package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottosGenerator {
    private static final int ONE = 1;
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final List<LottoNumber> lottoNumbersInBox = LottoNumberBox.create();

    static List<Lotto> generate(int lottosSize) {
        List<Lotto> lottos = new ArrayList<>();
        // todo: lottos를 Set<Lotto>로 만들어서 중복된 것 없게 만들기
        for (int i = 0; i < lottosSize; i++) {
            Set<LottoNumber> lottoNumbers = generateLottoNumbers();
            lottos.add(new Lotto(lottoNumbers));
        }
        return lottos;
    }

    private static Set<LottoNumber> generateLottoNumbers() {
        Collections.shuffle(lottoNumbersInBox);
        return new HashSet<>(
            Collections.unmodifiableCollection(lottoNumbersInBox.subList(ONE, ONE + LOTTO_NUMBER_SIZE)));
    }
}
