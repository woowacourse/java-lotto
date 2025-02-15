package view;

import model.LottoResult;
import model.Lottos;

public class OutputView {

    private OutputView() {

    }

    public static void inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printPurchaseCount(int count) {
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public static void printLottoResults(Lottos lottos) {
        lottos.printLottoNumbers();
    }

    public static void inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void winningStatistics(double result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원)- %d개%n", LottoResult.FIFTH.getCount());
        System.out.printf("4개 일치 (50000원)- %d개%n", LottoResult.FOURTH.getCount());
        System.out.printf("5개 일치 (1500000원)- %d개%n", LottoResult.THIRD.getCount());
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개%n", LottoResult.SECOND.getCount());
        System.out.printf("6개 일치 (2000000000원)- %d개%n", LottoResult.FIRST.getCount());
        System.out.printf("총 수익률은 %.02f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result);
    }

}
