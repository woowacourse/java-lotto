package lotto.domain.lotto;

import lotto.domain.lotto.generator.AutomaticLottoGenerator;
import lotto.domain.lotto.generator.LottoGenerator;
import lotto.domain.money.Money;

import java.util.List;

public class LottoMachine {

    public static Lottos generateLottos(List<Lotto> manualLottos, Money money) {
        LottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator(money.getLottoCount() - manualLottos.size());
        return new Lottos(manualLottos).append(automaticLottoGenerator.generate());
    }
}
