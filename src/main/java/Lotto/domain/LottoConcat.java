package Lotto.domain;

import java.util.List;

public class LottoConcat {
    public static Lottos concatLottos(Lottos autoLottos, Lottos manualLottos) {
        if (manualLottos == null) {
            return autoLottos;
        }
        List<Lotto> allLottos = autoLottos.getLottos();
        allLottos.addAll(manualLottos.getLottos());
        return new Lottos(allLottos);
    }
}
