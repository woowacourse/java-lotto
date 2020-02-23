package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.Results;
import lotto.domain.WinningInfo;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

public class OutputView {
    public static void printLottoCount(Payment payment) {
        System.out.println(payment.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottoList(List<Lotto> lottoList) {
        for (int i = 0; i < lottoList.size(); i++) {
            System.out.println(lottoList.get(i).getLottoNumbers().toString());
        }
    }

    public static void printResults(Results results) {
        TreeMap<WinningInfo, Long> summary = new TreeMap<>(results.getSummary());
        Iterator<WinningInfo> iteratorKey = summary.keySet().iterator();

        System.out.println("\n당첨 통계");
        System.out.println("---------");

        while (iteratorKey.hasNext()) {
            WinningInfo key = iteratorKey.next();

            System.out.println(winningString(key) + (summary.get(key) - 1) + "개");
        }
        System.out.println("총 수익률을 " + results.getEarningRate() + "%입니다.");
    }

    public static String winningString(WinningInfo key) {
        String bonusString = "";
        if (key.isHasBonus()) {
            bonusString = ", 보너스 볼 일치";
        }
        return key.getWinningCount() + "개 일치" + bonusString + "(" + key.getWinningPrice() + "원)- ";
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
