package lotto.model.creator;

import lotto.model.LottoRank;
import lotto.model.object.Payment;
import lotto.model.object.WinStats;
import lotto.model.object.Yield;

public class YieldCreator {
        public static Yield create(final Payment payment, final WinStats winStats) {
                int totalRevenue = 0;
                for (LottoRank lottoRank : LottoRank.values()) {
                        totalRevenue += lottoRank.getPrizes() * winStats.getCount(lottoRank);
                }
                return new Yield(payment.calculateYield(totalRevenue));
        }
}
