package View;

import Model.Lottos;

public class OutputView {

    public static void inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printPurchaseCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public static void printLottoResults(Lottos lottos){
        lottos.printLottoNumbers();
    }

    public static void inputWinnerNumbers(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void inputBonusBall(){
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void winningStatistics(){
        System.out.println("당첨 통계\n"
                + "---------");

    }

}
