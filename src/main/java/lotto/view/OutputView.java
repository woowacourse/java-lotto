package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Payment;
import lotto.domain.Results;
import lotto.domain.WinningInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class OutputView {
    public static void printLottoCount(Payment payment) {
        System.out.println(payment.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottosNumbers(Lottos lottos) {
        int lottosLength = lottos.getLottos().size();
        for (int i = 0; i < lottosLength; i++) {
            System.out.println(lottos.getLottos());
        }
    }

    public static void printResults(Results results) {
        Map<WinningInfo, Long> summary = results.getSummary();
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        TreeMap<WinningInfo, Long> treeMap = new TreeMap<>(summary);


        Iterator<WinningInfo> iteratorKey = treeMap.keySet().iterator();
        while (iteratorKey.hasNext()) {
            WinningInfo key = iteratorKey.next();

            System.out.println(key.toString() + (treeMap.get(key) - 1) + "개");
        }
        System.out.println("총 수익률을 " + results.getEarningRate() + "%입니다.");
    }
}
