package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    public static List<Lotto> create(List<String[]> manuals, Money money) {
        List<Lotto> lottos = new ArrayList<>();

        LottoCreator lottoCreator = new ManualLottoCreator(manuals);
        lottos.addAll(lottoCreator.create());
        lottoCreator = new AutoLottoCreator(money.getLottoSize() - manuals.size());
        lottos.addAll(lottoCreator.create());

        return lottos;
    }
}
