package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.model.LottoType.*;

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
    private final Map<LottoType, Lottos> lottosMap = new HashMap<>();
    private Lottos manualLottos;

    public LottoGame(long lottoMoney, int numberOfManualLottos, LottoFactory autoLottoFactory) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
        this.autoLottos = buyAutoLottos(autoLottoFactory);
        this.lottosMap.put(AUTO, this.autoLottos);
    }

    private Lottos buyAutoLottos(LottoFactory autoLottoFactory) {
        return new Lottos(autoLottoFactory, lottoMoney.getAutoLottoSize());
    }

    public void buyManualLottos(List<List<Integer>> inputManualLottos) {
        this.manualLottos = new Lottos(new ManualLottoFactory(inputManualLottos), inputManualLottos.size());
        lottosMap.put(MANUAL, this.manualLottos);
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

    public Map<LottoType, List<Lotto>> getLottos() {
        return lottosMap.entrySet()
            .stream()
            .collect(toUnmodifiableMap(Map.Entry::getKey,
                entry -> entry.getValue().getLottos(), (a, b) -> b));
    }
}
