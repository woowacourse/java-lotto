package view;

import java.util.List;
import model.Lotto;
import model.WinningResult;
import model.WinningStatus;

public class OutputView {
    public void printLottoCount(int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningResult(WinningResult winningResult) {
        for (WinningStatus winningStatus : winningResult.getWinningResults().keySet()) {
            if(winningStatus == WinningStatus.NONE) continue;
            int winningCount = winningResult.getWinningResults().get(winningStatus);
            System.out.printf("%s - %d개\n", winningStatus.getExpression(), winningCount);
        }
    }

    public void printEarningRate(double earningRate) {
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }
}
