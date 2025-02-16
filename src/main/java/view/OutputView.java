package view;

import java.util.List;
import model.LottoCalculator;
import model.LottoResult;

public class OutputView {

    private OutputView() {

    }

    public static void inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printPurchaseCount(int count) {
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public static void printLottoResults(List<String> lottoNumbers) {
        for (String lottoNumber : lottoNumbers) {
            System.out.println("[" + lottoNumber + "]");
        }
    }

    public static void inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void winningStatistics(LottoCalculator lottoCalculator, double result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (%d원)- %d개%n", LottoResult.FIFTH.getPrice(),
                lottoCalculator.findTargetResultCount(LottoResult.FIFTH));
        System.out.printf("4개 일치 (%d원)- %d개%n", LottoResult.FOURTH.getPrice(),
                lottoCalculator.findTargetResultCount(LottoResult.FOURTH));
        System.out.printf("5개 일치 (%d원)- %d개%n", LottoResult.THIRD.getPrice(),
                lottoCalculator.findTargetResultCount(LottoResult.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(%d원)- %d개%n", LottoResult.SECOND.getPrice(),
                lottoCalculator.findTargetResultCount(LottoResult.SECOND));
        System.out.printf("6개 일치 (%d원)- %d개%n", LottoResult.FIRST.getPrice(),
                lottoCalculator.findTargetResultCount(LottoResult.FIRST));
        System.out.printf("총 수익률은 %.02f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result);
    }
}
