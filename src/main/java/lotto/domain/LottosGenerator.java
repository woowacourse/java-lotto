package lotto.domain;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.CollectionLottoNumberShuffler;
import lotto.LottoNumberShuffler;

public class LottosGenerator {
    private static final int ONE = 1;
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static List<LottoNumber> lottoNumbersInBox = LottoNumberBox.create();
    private final LottoNumberShuffler lottoNumberShuffler;

    public LottosGenerator(LottoNumberShuffler lottoNumberShuffler) {
        this.lottoNumberShuffler = lottoNumberShuffler;
    }

    Lottos generate(int lottosSize) {
        Set<Lotto> lottos = new HashSet<>();
        for (int i = 0; i < lottosSize; i++) {
            Set<LottoNumber> lottoNumbers = generateLottoNumbers();
            lottos.add(new Lotto(lottoNumbers));
        }
        return new Lottos(lottos);
    }

    private Set<LottoNumber> generateLottoNumbers() {
        lottoNumbersInBox = lottoNumberShuffler.shuffle(lottoNumbersInBox);
        return new HashSet<>(
            Collections.unmodifiableCollection(lottoNumbersInBox.subList(ONE, ONE + LOTTO_NUMBER_SIZE)));
    }
}
