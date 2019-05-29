package lotto;

import java.util.*;

public class LottoMachine {
    private static final int LOTTO_NUMBER_COUNT = 6;
    public static Lottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return new Lottos(lottos);
    }

    private static Lotto generateLotto() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        while ( lottoNumbers.size() < LOTTO_NUMBER_COUNT ) {
            lottoNumbers.add(new LottoNumber(Random.generateLottoNumber()));
        }

        return new Lotto(lottoNumbers);
    }
}
