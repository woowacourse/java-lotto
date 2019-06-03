package lotto.model;

import java.util.List;

public class Wallet {

    public static Lottos buyLottos(Money money, List<Lotto> manualLottos) {
        LottoMachine manualLottoMachine = new ManualLottoMachine(manualLottos);
        LottoMachine automaticLottoMachine = new AutomaticLottoMachine(money, manualLottos.size());
        Lottos manualLottosOrganized = purchaseLottos(manualLottoMachine);
        Lottos automaticLottos = purchaseLottos(automaticLottoMachine);
        return manualLottosOrganized.append(automaticLottos);
    }

    private static Lottos purchaseLottos(LottoMachine machine) {
        return machine.generateLottos();
    }
}
