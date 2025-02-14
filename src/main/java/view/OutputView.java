package view;

import java.util.List;
import model.Lotto;
import model.WinningResult;
import model.WinningStatus;

public class OutputView {

    public void printPurchaseAmountInstruction() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printWinningNumbersInstruction() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumbersInstruction() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
    public void printLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers().stream().sorted().toList());
        }
    }

    public void printWinningResult(WinningResult winningResult) {
        System.out.println("\n당첨 통계\n---------");
        for (WinningStatus winningStatus : winningResult.getWinningResults().keySet()) {
            if(winningStatus == WinningStatus.NONE) continue;
            int winningCount = winningResult.getWinningResults().get(winningStatus);
            System.out.printf("%s - %d개\n", winningStatus.getExpression(), winningCount);
        }
    }

    public void printEarningRate(double earningRate) {
        String instruction = "";
        if (earningRate > 1) {
            instruction = "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
        }
        if (earningRate < 1) {
            instruction = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        System.out.printf("총 수익률은 %.2f입니다. %s", earningRate, instruction);
    }
}
