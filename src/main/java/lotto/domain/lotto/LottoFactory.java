package lotto.domain.lotto;

import lotto.domain.creator.LottoCreator;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static Lottos createLottos(List<LottoCreator> creators) {
        List<Lotto> lottos = new ArrayList<>();

        for (LottoCreator creator : creators) {
            lottos.add(creator.createLotto());
        }

        return new Lottos(lottos);
    }

}
