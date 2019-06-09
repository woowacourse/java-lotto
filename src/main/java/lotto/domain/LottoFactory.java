package lotto.domain;

import java.util.List;

public class LottoFactory {
    public static List<Lotto> createLottoList(int lottoQuantity, LottoCreator creator) {
        return creator.createLottos(lottoQuantity);
    }
}
