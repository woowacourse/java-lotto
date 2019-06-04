package lotto.creator;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    public static Lottos create(List<String> manuals, Money money) {
        List<Lotto> lottos = new ArrayList<>();

        LottoCreator lottoCreator = new ManualLottoCreator(manuals);
        lottos.addAll(lottoCreator.create());
        lottoCreator = new AutoLottoCreator(money.getLottoSize() - manuals.size());
        lottos.addAll(lottoCreator.create());

        return new Lottos(lottos);
    }
}
