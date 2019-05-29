package lotto.view;

import lotto.model.LottoRank;
import lotto.model.object.*;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;
        private static final int LOTTO_PRICE = 1000;
        private static final int FOURTH_MATCH_NUMBER = 3;

        public static void printPurchaseNumber(Payment payment) {
                System.out.println(payment.getNumber() / LOTTO_PRICE + "개를 구매했습니다.");
        }

        public static void printPurchaseLottos(List<Lotto> lottos) {
                for (Lotto lotto : lottos) {
                        printEachLotto(lotto);
                }
                System.out.println();
        }

        private static void printEachLotto(Lotto lotto) {
                List<Integer> lottonumbers =  new ArrayList<>();
                for(LottoNumber lottoNumber : lotto.getLottoNumbers()){
                        lottonumbers.add(lottoNumber.getNumber());
                }
                System.out.println(lottonumbers.toString());
        }

        public static void printWinStats(WinStats winStats) {
                System.out.println("\n당첨 통계");
                System.out.println("---------");
                for (int matchNumber = FOURTH_MATCH_NUMBER; matchNumber <= NUMBER_OF_LOTTO_NUMBERS; matchNumber++){
                        System.out.println(matchNumber + "개 일치 " + "("
                                        + LottoRank.getPrizes(matchNumber)
                                        + "원)- " + winStats.getMappingStats().get(LottoRank.getLottoRank(matchNumber)) + "개");
                }
        }

        public static void printYield(Yield yield) {
                System.out.println("총 수익률은 " + yield.getNumber() + "%입니다.");
        }
}
