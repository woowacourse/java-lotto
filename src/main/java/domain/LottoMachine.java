package domain;

import domain.strategy.ManualLottoNumberStrategy;
import domain.strategy.AutoLottoNumberStrategy;

import java.util.*;

public class LottoMachine {

    public static final int LOTTO_TICKET_PRICE = 1000;
    private static final int INCREASE_VALUE = 1;

    public Lottos purchase(List<List<Integer>> manualLottoNumbers, PurchaseCount purchaseCount) {
        Lottos manualLottos = new Lottos(new ManualLottoNumberStrategy(manualLottoNumbers), purchaseCount);
        Lottos autoLottos = new Lottos(new AutoLottoNumberStrategy(), purchaseCount);

        return manualLottos.concatenate(autoLottos);
    }

    public WinningStat createWinningStat(Lottos lottos, WinLotto winLotto) {
        Map<LottoRank, Integer> ranks = new HashMap<>(); // enumMap 굿
        initializeRank(ranks);

        for (LottoNumbers lotto : lottos.getLottos()) {
            ranks.merge(winLotto.rank(lotto), INCREASE_VALUE, Integer::sum);
        }

        return new WinningStat(ranks);
    }

    private void initializeRank(Map<LottoRank, Integer> ranks) {
        for (LottoRank lottoRank : LottoRank.values()) {
            ranks.put(lottoRank, 0);
        }
    }
}
