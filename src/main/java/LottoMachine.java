import domain.*;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int manualLottoCount = InputView.askManualLottoCount();
        int autoLottoCount = money.getAutoLottoCount(manualLottoCount);
        Lottos manualLottos = InputView.askManualLottoNumbers(manualLottoCount);
        OutputView.printCountOfLotto(autoLottoCount, manualLottoCount);
        Lottos totalLottos = getLottos(autoLottoCount, manualLottos);
        WinningLotto winningNumber = inputWinningNumber();
        getStatistics(totalLottos, winningNumber, money);
    }

    private Lottos getLottos(int autoLottoCount, Lottos manualLottos) {
        Lottos autoLottos = Lottos.generateAutoLottos(autoLottoCount);
        Lottos totalLottos = manualLottos.concat(autoLottos);
        OutputView.printLottos(totalLottos);
        return totalLottos;
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
