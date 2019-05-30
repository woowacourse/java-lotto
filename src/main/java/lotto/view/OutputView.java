package lotto.view;

import lotto.model.LottoRank;
import lotto.model.object.*;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
        private static final int MIN_WIN_MATCH_NUMBER = 3;
        private static final int MATCH_NUMBER_FIVE = 5;
        private static final int MATCH_NUMBER_SIX = 6;
        private static final String PRINT_STATS_FORMAT = "%d개 일치 (%d원)- %d개\n";
        private static final String PRINT_STATS_FORMAT_SECOND = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
        private static final int LOTTO_PRICE = 1000;

        public static void printPurchaseLottos(List<Lotto> lottos) {

                for (Lotto lotto : lottos) {
                        printEachLotto(lotto);
                }
                System.out.println();
        }

        private static void printEachLotto(Lotto lotto) {
                List<Integer> lottonumbers = new ArrayList<>();
                for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
                        lottonumbers.add(lottoNumber.getNumber());
                }
                System.out.println(lottonumbers.toString());
        }

        public static void printWinStats(WinStats winStats) {
                System.out.println("\n당첨 통계");
                System.out.println("---------");
                for (int matchNumber = MIN_WIN_MATCH_NUMBER; matchNumber <= MATCH_NUMBER_FIVE; matchNumber++) {
                        System.out.printf(PRINT_STATS_FORMAT, matchNumber, LottoRank.getPrizes(matchNumber, false),
                            winStats.getMappingStats().get(LottoRank.getLottoRank(matchNumber, false)));
                }
                System.out.printf(PRINT_STATS_FORMAT_SECOND, MATCH_NUMBER_FIVE, LottoRank.getPrizes(MATCH_NUMBER_FIVE, true),
                    winStats.getMappingStats().get(LottoRank.getLottoRank(MATCH_NUMBER_FIVE, true)));
                System.out.printf(PRINT_STATS_FORMAT, MATCH_NUMBER_SIX, LottoRank.getPrizes(MATCH_NUMBER_SIX, false),
                    winStats.getMappingStats().get(LottoRank.getLottoRank(MATCH_NUMBER_SIX, false)));
        }

        public static void printYield(Yield yield) {
                System.out.println("총 수익률은 " + yield.getRate() * 100 + "%입니다.");
        }

        public static void printPurchaseHistory(Payment payment, ManualPurchaseNumber manualPurchaseNumber) {
                int autoPaymentNumber = (payment.getAmount() / LOTTO_PRICE) - manualPurchaseNumber.getNumber();
                System.out.println("\n수동으로 " + manualPurchaseNumber.getNumber() + "장, " + "자동으로 " + autoPaymentNumber + "개를 구매했습니다.");
        }
}
