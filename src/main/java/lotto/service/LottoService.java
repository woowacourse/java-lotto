package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoService {

    private static final int LOTTO_PRICE = 1000;

    public LottoTicket issueTicket(long purchaseAmount) {
        int count = countNumberOfPurchases(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = LottoNumberGenerator.generate();
            OutputView.printLottos(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers));
        }
        return new LottoTicket(lottos);
    }

    public int countNumberOfPurchases(long purchaseAmount) {
        return (int) purchaseAmount / LOTTO_PRICE;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> winningInfo, long purchaseAmount) {
        long totalProfit = sumTotalProfit(winningInfo);
        return (double) totalProfit / purchaseAmount;
    }

    private long sumTotalProfit(Map<LottoRank, Integer> winningInfo) {
        long sum = 0;
        for (LottoRank lottoRank : winningInfo.keySet()) {
            sum += lottoRank.calculateWinningAmount(winningInfo.get(lottoRank));
        }

        return sum;
    }
}
