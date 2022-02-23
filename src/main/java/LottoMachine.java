
public class LottoMachine {

    public void start() {

        Money money = new Money(InputView.askInputMoney());
        int count = money.generateCount();
        OutputView.printCountOfLotto(count);
        Lottos lottos = new Lottos();
        lottos.generateLottos(count);
        OutputView.printLottos(lottos);
        WinningNumber winningNumber = InputView.askInputWinningNumber();
        int bonusBall = InputView.askInputBonusBall();
        winningNumber.checkBonusBall(bonusBall);

        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber, bonusBall);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}
