package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    private final List<String> manualLottoNumbers;
    private final LottoCount lottoCount;

    public LottosFactory(List<String> manualLottoNumbers, LottoCount lottoCount) {
        this.manualLottoNumbers = manualLottoNumbers;
        this.lottoCount = lottoCount;
    }

    public Lottos generateTotalLottos() {
        List<Lotto> lottos = new ArrayList<>();
        getManualLottos(lottos);
        getAutoLottos(lottos);
        return new Lottos(lottos);
    }

    private void getManualLottos(List<Lotto> lottos) {
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine(manualLottoNumbers);
        List<Lotto> manualLottos = new ArrayList<>(manualLottoMachine.generateLottos());
        lottos.addAll(manualLottos);
    }

    private void getAutoLottos(List<Lotto> lottos) {
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine(lottoCount.getAutoCount());
        List<Lotto> autoLottos = new ArrayList<>(autoLottoMachine.generateLottos());
        lottos.addAll(autoLottos);
    }
}