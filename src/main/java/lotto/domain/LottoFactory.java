package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> createLottos(int num, LottoCreator lottoCreator) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            lottos.add(lottoCreator.create());
        }
        return lottos;
    }
}
