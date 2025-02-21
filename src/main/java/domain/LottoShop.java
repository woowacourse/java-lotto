package domain;

import java.util.ArrayList;
import java.util.List;
import util.RandomNumbersGenerator;

public class LottoShop {
    public LottoWallet generateLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(LottoNumber.MIN, LottoNumber.MAX, 6)));
        }
        return new LottoWallet(lottos);
    }
}
