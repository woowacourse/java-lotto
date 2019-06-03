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
        for (int i = 0; i < lottoCount.getManualCount(); i++) {
            ManualLottoMachine lottoMachine = new ManualLottoMachine(manualLottoNumbers.get(i));
            lottosList.add(lottoMachine.generateLotto());
        }
    }

    private void getAutoLottos() {
        AutoLottoMachine lottoMachine = new AutoLottoMachine();
        for (int i = 0; i < lottoCount.getAutoCount(); i++) {
            lottosList.add(lottoMachine.generateLotto());
        }
    }
}
