package lotto.model;

import static java.util.stream.Collectors.*;
import static lotto.model.LottoType.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.model.lottofactory.Lotto;
import lotto.model.lottofactory.LottoFactory;
import lotto.model.lottofactory.ManualLottoFactory;

public class LottoGame {
    private final LottoMoney lottoMoney;
    private final Map<LottoType, Lottos> lottosMap = new EnumMap<>(LottoType.class);

    public LottoGame(long lottoMoney, int numberOfManualLottos) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
    }

    public void buyLottos(List<List<Integer>> inputManualLottos, LottoFactory autoLottoFactory) {
        buyManualLottos(inputManualLottos);
        buyAutoLottos(autoLottoFactory);
    }

    private void buyManualLottos(List<List<Integer>> inputManualLottos) {
        lottosMap.putIfAbsent(MANUAL, new Lottos(new ManualLottoFactory(Collections.emptyList()), 0));
        lottosMap.put(MANUAL, new Lottos(new ManualLottoFactory(inputManualLottos), inputManualLottos.size()));
    }

    private void buyAutoLottos(LottoFactory autoLottoFactory) {
        lottosMap.put(AUTO, new Lottos(autoLottoFactory, lottoMoney.getAutoLottoSize()));
    }

    public LottoResult generateLottoResult(List<Integer> winningNumbers, int bonusNumber) {
        return new LottoResult(lottosMap, winningNumbers, bonusNumber);
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
