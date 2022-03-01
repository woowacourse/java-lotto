import domain.*;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int totalLottoCount = money.generateCount();
        int manualLottoCount = InputView.askManualLottoCount(totalLottoCount);
        ManualLottos manualLottos = InputView.askManualLottoNumbers(manualLottoCount);
        OutputView.printCountOfLotto(totalLottoCount, manualLottoCount);
        Lottos totalLottos = getLottos(totalLottoCount - manualLottoCount, manualLottos);
        WinningLotto winningNumber = inputWinningNumber();
        getStatistics(totalLottos, winningNumber, money);
    }

    private Lottos getLottos(int autoLottoCount, ManualLottos manualLottos) {
        AutoLottos autoLottos = AutoLottos.generateLottos(autoLottoCount);
        Lottos totalLottos = Lottos.add(manualLottos, autoLottos);
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
