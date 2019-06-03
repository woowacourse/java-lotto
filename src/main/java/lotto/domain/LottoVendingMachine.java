package lotto.domain;

import java.util.List;

public class LottoVendingMachine {
    public static Lottos getLottos(LottoCount lottoCount, List<List<Integer>> customLottoNumbers) {
        if (customLottoNumbers.size() != lottoCount.custom()) {
            throw new InvalidCustomLottoNumbersException("수동으로 구매하기로 한 로또 수와 일치하지 않습니다.");
        }
        Lottos userLottos = LottoFactory.createCustomLottos(customLottoNumbers);
        return userLottos.add(LottoFactory.createRandomLottos(lottoCount.random()));
    }
}