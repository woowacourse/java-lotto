package lotto.domain.lotto;

import lotto.domain.lotto.generator.AutomaticLottoGenerator;
import lotto.domain.lotto.generator.LottoGenerator;
import lotto.domain.lotto.generator.ManualLottoGenerator;
import lotto.domain.money.Money;

import java.util.List;

public class LottoMachine {

    public static Lottos generateLottos(List<List<Integer>> manualLottos, Money money) {
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(manualLottos);
        LottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator(money.getLottoCount() - manualLottos.size());
        return manualLottoGenerator.generate().append(automaticLottoGenerator.generate());
    }

}
