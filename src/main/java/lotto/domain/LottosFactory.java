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
        List<Lotto> lottosList = new ArrayList<>();
        getManualLottos(lottosList);
        getAutoLottos(lottosList);
        return new Lottos(lottosList);
    }

    private void getManualLottos(List<Lotto> lottosList) {
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine(manualLottoNumbers);
        List<Lotto> manualLottos = new ArrayList<>(manualLottoMachine.generateLottos());
        for (Lotto manualLotto : manualLottos) {
            lottosList.add(manualLotto);
        }
    }

    private void getAutoLottos(List<Lotto> lottosList) {
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine(lottoCount.getAutoCount());
        List<Lotto> autoLottos = new ArrayList<>(autoLottoMachine.generateLottos());
        for (Lotto autoLotto : autoLottos) {
            lottosList.add(autoLotto);
        }
    }
}