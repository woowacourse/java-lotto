package Lotto.domain;

import java.util.List;

public class LottoConcat {
    public static Lottos concatLottos(Lottos autoLottos, Lottos manualLottos) {
        List<Lotto> allLotto = autoLottos.getLottos();
        if (manualLottos != null) {
            allLotto.addAll(manualLottos.getLottos());
        }
        return new Lottos(allLotto);
    }
}
