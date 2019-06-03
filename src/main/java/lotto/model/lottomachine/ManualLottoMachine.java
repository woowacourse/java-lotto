package lotto.model.lottomachine;


import lotto.model.Lotto;
import lotto.model.Lottos;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoMachine implements LottoMachine {
    private List<Lotto> manualLottos;

    public ManualLottoMachine(List<Lotto> manualLottos) {
        this.manualLottos = manualLottos;
    }

    @Override
    public Lottos generateLottos() {
        List<Lotto> newManualLottos = new ArrayList<>(manualLottos);
        return new Lottos(newManualLottos);
    }
}
