package lotto.model.generator;

import lotto.model.Lottos;

public interface LottoGenerator {
    Lottos generateLottos(int lottoCount, int minimumNumber, int maximumNumber, int lottoLength);
}
