package lotto.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.model.lottofactory.Lotto;
import lotto.model.lottofactory.LottoFactory;
import lotto.model.lottofactory.ManualLottoFactory;

public class LottoGame {
    private final Lottos autoLottos;
    private final LottoMoney lottoMoney;
    private Lottos manualLottos;

    public LottoGame(long lottoMoney, int numberOfManualLottos, LottoFactory autoLottoFactory) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
        this.autoLottos = buyAutoLottos(autoLottoFactory);
    }

    private Lottos buyAutoLottos(LottoFactory autoLottoFactory) {
        return new Lottos(autoLottoFactory, lottoMoney.getAutoLottoSize());
    }

    public void buyManualLottos(List<List<Integer>> inputManualLottos) {
        this.manualLottos = new Lottos(new ManualLottoFactory(inputManualLottos), inputManualLottos.size());
    }

    public LottoResult generateLottoResult(List<Integer> winningNumbers, int bonusNumber) {
        if (manualLottos == null) {
            manualLottos = new Lottos(new ManualLottoFactory(Collections.emptyList()), 0);
        }
        return new LottoResult(manualLottos, autoLottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(LottoResult lottoResult) {
        return lottoResult.calculateYield(lottoMoney);
    }

    public List<Lotto> getAutoLottos() {
        return autoLottos.getLottos();
    }

    public Map<String, List<Lotto>> getLottos() {
        Map<String, List<Lotto>> lottosMap = new HashMap<>();
        lottosMap.put("Manual", manualLottos.getLottos());
        lottosMap.put("Auto", autoLottos.getLottos());
        return lottosMap;
    }
}
