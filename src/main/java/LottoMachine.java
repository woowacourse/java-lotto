import domain.*;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int totalLottoCount = money.generateCount();
        int manualLottoCount = InputView.askManualLottoCount(totalLottoCount);

        OutputView.printCountOfLotto(totalLottoCount, manualLottoCount);
        Lottos lottos = createLottos(totalLottoCount);
        WinningLotto winningNumber = inputWinningNumber();
        getStatistics(lottos, winningNumber, money);
    }

    private Lottos createLottos(int lottoCount) {
        Lottos lottos = Lottos.generateLottos(lottoCount);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private WinningLotto inputWinningNumber() {
        Lotto winningNumber = InputView.askInputWinningNumber();
        LottoNumber bonusBall = InputView.askInputBonusBall();
        return new WinningLotto(winningNumber, bonusBall);
    }

    private void getStatistics(Lottos lottos, WinningLotto winningNumber, Money money) {
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}
