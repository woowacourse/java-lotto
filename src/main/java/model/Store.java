package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    private final LottoMachine lottoMachine;

    public Store(final LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public List<Lotto> purchaseLottos(final PurchaseAmount purchaseAmount) {
        final int lottoCount = purchaseAmount.calculateLottoCount();
        return lottoMachine.issueLottos(lottoCount);
    }

    public WinningResult calculateWinningResult(
        final WinningNumbers winningNumbers,
        final List<Lotto> lottos
    ) {
        final Map<LottoRank, Integer> lottoRanks = new HashMap<>();
        lottos.forEach(lotto -> {
            final LottoRank lottoRank = lottoMachine.checkWinningRank(lotto, winningNumbers);
            lottoRanks.put(lottoRank, lottoRanks.getOrDefault(lottoRank, 0) + 1);
        });

        return new WinningResult(lottoRanks);
    }
}
