package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    private final List<List<Integer>> manualLottoNumbers;
    private final LottoCount lottoCount;
    private List<Lotto> lottosList = new ArrayList<>();

    public LottosFactory(List<List<Integer>> manualLottoNumbers, LottoCount lottoCount) {
        this.manualLottoNumbers = manualLottoNumbers;
        this.lottoCount = lottoCount;
    }

    public Lottos getLottos() {
        getManualLottos();
        getAutoLottos();
        return new Lottos(lottosList);
    }

    private void getManualLottos() {
        ManualLottoMachine manualLottoMachine = new ManualLottoMachine(manualLottoNumbers);
        List<Lotto> manualLottos = new ArrayList<>(manualLottoMachine.generateLottos());
        for (Lotto manualLotto : manualLottos) {
            lottosList.add(manualLotto);
        }
    }

    private void getAutoLottos() {
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine(lottoCount.getAutoCount());
        List<Lotto> autoLottos = new ArrayList<>(autoLottoMachine.generateLottos());
        for (Lotto autoLotto : autoLottos) {
            lottosList.add(autoLotto);
        }
    }
}
