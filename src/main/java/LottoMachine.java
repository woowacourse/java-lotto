import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int lottoCount = money.generateCount();
        OutputView.printCountOfLotto(lottoCount);
        Lottos lottos = createLottos(lottoCount);
        WinningNumber winningNumber = inputWinningNumber();
        getStatistics(lottos, winningNumber, money);
    }

    private Lottos createLottos(int lottoCount) {
        Lottos lottos = Lottos.generateLottos(lottoCount);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private WinningNumber inputWinningNumber() {
        List<Integer> winningNumber = InputView.askInputWinningNumber();
        int bonusBall = InputView.askInputBonusBall();
        return new WinningNumber(winningNumber, bonusBall);
    }

    private void getStatistics(Lottos lottos, WinningNumber winningNumber, Money money) {
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}
