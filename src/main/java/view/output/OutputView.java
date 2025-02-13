package view.output;

import java.util.List;
import java.util.Map;
import model.LottoRank;

public interface OutputView {

    void printPurchaseQuantity(final int purchaseQuantity);

    void printLottoNumbers(final List<List<Integer>> lottoNumbers);

    void printLottoStatistics(final double revenueRate, final Map<LottoRank, Integer> lottoRanks,
                              final boolean isDamage);
}
