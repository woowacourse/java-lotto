package view;

import domain.LottoNumbers;
import domain.WinPrice;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

public class OutputView {
    public static void printMoneyInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printAutoLotto(final List<LottoNumbers> issuedLotto) {
        System.out.println(issuedLotto.size() + "개를 구매했습니다.");
        for (LottoNumbers lottoNumbers : issuedLotto) {
            System.out.println(lottoNumbers.getLottoNumbers());
        }
    }

    public static void printWinNumbersInstruction() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusInstruction() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printWinStatistics(final SortedMap<WinPrice, Integer> result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Entry<WinPrice, Integer> entry : result.entrySet()) {
            final WinPrice winPrice = entry.getKey();
            if (winPrice == WinPrice.SECOND) {
                System.out.println(
                        winPrice.getCount() - 2 + "개 일치, 보너스 볼 일치(" + winPrice.getPrice() + "원)- " + entry.getValue()
                                + "개");
                continue;
            }
            System.out.println(winPrice.getCount() + "개 일치 (" + winPrice.getPrice() + "원)- " + entry.getValue() + "개");
        }
    }

    public static void printWinProfit(final double calculateProfit) {
        System.out.println(String.format("총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 손해라는 의미임)", calculateProfit));
    }
}
