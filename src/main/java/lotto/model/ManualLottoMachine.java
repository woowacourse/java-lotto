package lotto.model;

import lotto.LottoLauncher;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoMachine implements LottoMachine {
    private ManualLottoCount manualLottoCount;

    public ManualLottoMachine(ManualLottoCount manualLottoCount) {
        this.manualLottoCount = manualLottoCount;
    }

    @Override
    public Lottos generateLottos() {
        List<Lotto> manualLottos = new ArrayList<>();
        int currentCount = 0;
        while (manualLottoCount.isCountFinished(currentCount)) {
            manualLottos.add(new Lotto(LottoLauncher.getManualLottoNumbers()));
            currentCount++;
        }
        return new Lottos(manualLottos);
    }
}
