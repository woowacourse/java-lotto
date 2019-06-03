package lotto.model;

import lotto.model.lottomachine.AutomaticLottoMachine;
import lotto.model.lottomachine.LottoMachine;
import lotto.model.lottomachine.ManualLottoMachine;

import java.util.List;

public class LottoService {

    public static Lottos produceLottos(Money money, List<Lotto> manualLottos) {
        LottoMachine manualLottoMachine = new ManualLottoMachine(manualLottos);
        LottoMachine automaticLottoMachine = new AutomaticLottoMachine(money, manualLottos.size());
        Lottos manualLottosOrganized = createLottos(manualLottoMachine);
        Lottos automaticLottos = createLottos(automaticLottoMachine);
        return manualLottosOrganized.append(automaticLottos);
    }

    private static Lottos createLottos(LottoMachine machine) {
        return machine.generateLottos();
    }
}
